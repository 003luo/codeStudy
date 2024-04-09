package com.codestudy.controller;

import com.codestudy.entity.Result1;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Api(tags = "添加文件接口")
@RestController
@Slf4j
@CrossOrigin  //运行跨域
@RequestMapping("/upload")
public class UploadController {

    //添加文件--本地存储
    @PostMapping("/in")
    public Result1 upload(Integer noteId, MultipartFile file) throws Exception {

        //注意MultipartFile 变量名要和前端传过来的名字对应
        System.out.println("filename:" + file);
        String originalFilename = file.getOriginalFilename();  //获取原始文件名  xxx.jpg
        int index = originalFilename.lastIndexOf(".");  //获取最后一个点所在的索引
        String extname = originalFilename.substring(index);  //截取最后一个点所在的索引及其后面的字符  .jpg
        String newFileName = UUID.randomUUID().toString() + extname; //通过uuid加后缀名构造唯一的文件名

        System.out.println("新文件名：" + newFileName);  //文件名

        //将笔记id，文件名存到表中


        //将文件存储在服务器的磁盘目录中
        file.transferTo(new File("D:\\A10\\A-files\\" + newFileName));

        return Result1.success("上传成功");
    }

    //查询文件
    @PostMapping("/out")
    public FileInputStream findFiles(Integer noteId) throws IOException {
        String filename = "4cccdb28-e4bc-4bb0-8663-6850d80e9adb.jpg";  //图片名

        //创建一个文件输入流,通过地址拿到文件,读入到java程序
        FileInputStream in = new FileInputStream("D:\\A10\\A-files\\" + filename);
        System.out.println("===>in:" + in);

        FileOutputStream out =new FileOutputStream("D:\\A10\\A-files\\wwww.jpg");  //创建文件输出流，将读取的文件数据，返回给前端
        int readData = 0;  //从输入流中读取一个字节Byte的数据,如果没有输入可用，此方法将阻止
        while ((readData = in.read()) != -1) {  //如果读取到-1表示读取完毕
//            System.out.println(readData);
            out.write(readData); //读取部分数据就写入，因为内存有限
        }
        System.out.println("====>out:" + out);
        in.close();  //关闭文件输入流，释放资源
        out.close();  //关闭输出流

        return in;
    }

    //删除文件



    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        // 读取文件内容
        File file = new File("D:\\A10\\A-files\\wwww.jpg");
        FileInputStream inputStream = new FileInputStream(file);

        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());

        // 创建ResponseEntity对象并返回
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(inputStream));
    }

}
