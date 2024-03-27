package com.yy.controller;

import ch.qos.logback.core.util.FileUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.UrlResource;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Result;
import com.yy.entity.User;
import com.yy.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private UserService userService;
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file,@RequestParam("userId")int userId) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String flag = IdUtil.fastSimpleUUID();
        String rootfilepath = System.getProperty("user.dir") + "/src/main/resources/files/" + flag +"_"+ originalFilename;
        Files.write(Paths.get(rootfilepath), file.getBytes());
        User user = userService.getById(userId);
        user.setImag("/files/download/"+flag);
        userService.saveOrUpdate(user);
        return Result.success("/files/download/"+flag);

    }
    @GetMapping("/download/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os = null;
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/";
        File folder = new File(basePath);
        File[] files = folder.listFiles((dir, name) -> name.contains(flag));
        if (files != null && files.length > 0) {
            File file = files[0]; // 假设只有一个匹配的文件，取第一个
            try (FileInputStream fis = new FileInputStream(file)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8));
                response.setContentType("application/octet-stream");
                os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.flush();
            } catch (IOException e) {
                System.out.println("下载文件时发生异常：" + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        System.out.println("关闭输出流时发生异常：" + e.getMessage());
                    }
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
