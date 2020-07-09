package com.tz.leo.ssm.springmvc.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Author: tz_wl
 * Date: 2020/7/8 19:22
 * Content:
 */

@Controller
@RequestMapping("file")
public class FileController {

    //用来处理文件下载 请求对应响应输出流
    @RequestMapping("download")
    public void download(String openStyle, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        openStyle= (openStyle==null)? "inline":"attachment";
        System.out.println("下载文件名称："+fileName);
        //1.根据下载相对目录获取下载目录在服务器部署之后绝对目录
        String realPath=request.getSession().getServletContext().getRealPath("/down");
        //2.通过文件输入流读取文件
        FileInputStream is=new FileInputStream(new File(realPath,fileName));
        //3.获取响应输出流      //4.附件下载attachment 附件  inline 在线打开
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        ServletOutputStream os= response.getOutputStream();
        //5.处理下载流复制 //操作io流用IOUtils  操作file 用 FileUtils
        //IOUtils 类需要因引入jar 包
        // <groupId>commons-fileupload</groupId>
        // <artifactId>commons-fileupload</artifactId>
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);


        /*//5.2传统写法
        //aa =22034
        int len;
        byte[] b =new byte[1024];
        while (true){
            len =is.read(b);
            if(len==-1){break;}
            os.write(b,0,len);
        }
        os.close();
        is.close();*/
    }


    //用来处理文件上传
    @RequestMapping("upload")
    public String upload(MultipartFile fileName,HttpServletRequest request) throws IOException {
        System.out.println("文件名"+fileName.getName());
        System.out.println("文件大小"+fileName.getSize());
        System.out.println("文件类型"+fileName.getContentType());
        //文件上传
        //1.根据upload相对路径获取部署到服务之后绝对路径
        String realPath=request.getSession().getServletContext().getRealPath("/upload");

        //2.修改文件原始名称
        String extension= org.apache.commons.io.FilenameUtils.getExtension(fileName.getOriginalFilename());
        String newFileName= UUID.randomUUID().toString().replace("-","")+"."+ extension;

        //3.生成当天日期目录
        LocalDate now=LocalDate.now();
        File dateDir=new File(realPath,now.toString());
        if(!dateDir.exists()){dateDir.mkdir();}

        //4.将文件上传到upload对应日期的目录中
        fileName.transferTo(new File(dateDir,newFileName));
        return "fileUploadSuccess";
    }


}
