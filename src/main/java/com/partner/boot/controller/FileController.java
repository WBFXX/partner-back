package com.partner.boot.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.partner.boot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 文件处理相关接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    private static final String FILES_DIR = "/files/";
    //yml文件里的值
    @Value("${file.upload.path:}")
    private String uploadPath;
    @Value("${server.port:9090}")
    private String port;
    @Value("${file.download.ip:localhost}")
    private String downloadIP;

    /**
     * 文件上传
     * @param file
     * @return Result
     */
    @PostMapping("/upload")
    public Result upload( MultipartFile file){

        String originalFilename = file.getOriginalFilename(); //文件完整的名称
        String extName = FileUtil.extName(originalFilename);  //文件后缀名
        String fileName = FileUtil.mainName(originalFilename);//文件主名称
        String uniFileFlag = IdUtil.fastSimpleUUID(); //文件唯一表示uuid
        String fileFullName = uniFileFlag + StrUtil.DOT + extName;//文件名
//        uploadPath += FILES_DIR + uniFileFlag + StrUtil.DOT + extName;//XXX.JPG
        //封装完整的文件路径获取方法
        String fileUploadPath = getFileUploadPath(fileFullName);
        long size = file.getSize();  //单位是 byte,size/1024 -> kb
//        byte[] bytes = file.getBytes();
        String name = file.getName();//参数名
        log.info("{},{},{}",originalFilename,size,name);

        try {
            File uploadFile = new File(fileUploadPath);//流文件转为本地磁盘文件
            File parentFile = uploadFile.getParentFile();
            if (!parentFile.exists()) {//如果父级不存在，也就是说files目录不存在，那么我要创建出来一个目录
                parentFile.mkdirs();//创建目录
            }

            file.transferTo(uploadFile); //把内存图片写入磁盘中
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败");
        }
        return Result.success("http://" + downloadIP + ":" + port + "/file/download/" + fileFullName);

    }

    /**
     * 文件下载
     * @param fileFullName
     * @param response
     * @throws IOException
     */
    @GetMapping("/download/{fileFullName}")
    public void downloadFile(@PathVariable String fileFullName,@RequestParam(required = false)String loginId,
                             @RequestParam(required = false)String token,
                             HttpServletResponse response) throws IOException {
        //token校验
//        List<String> tokenList = StpUtil.getTokenValueListByLoginId(loginId);
//        if (CollUtil.isEmpty(tokenList) || !tokenList.contains(token)) {
//            return;
//        }

        String fileUploadPath = getFileUploadPath(fileFullName);//通过文件名拿到文件路径
        byte[] bytes = FileUtil.readBytes(fileUploadPath);//将文件路径存到byte数组
        OutputStream os = response.getOutputStream();//输出流
        os.write(bytes);
        os.flush();
        os.close();
    }

    /**
     * 获取文件的完整路径
     * @param fileFullName
     * @return uploadPath
     */
    private String getFileUploadPath(String fileFullName){
        if(StrUtil.isBlank(uploadPath)){
            uploadPath = System.getProperty("user.dir");
        }
        return uploadPath + FILES_DIR + fileFullName;// 完整的文件路径 前+后

    }

}
