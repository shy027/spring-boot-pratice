package top.shy.springboot.file.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.shy.springboot.file.utils.MinIoTemplate;
import top.shy.springboot.file.utils.OssTemplate;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadController {
    @Value("${file.upload-path}")
    private String uploadPath;
    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    @Resource
    private MinIoTemplate minIoTemplate;
    @Resource
    private OssTemplate ossTemplate;
    @PostMapping("/native")
    public String nativeUpload(MultipartFile file, HttpServletRequest request) {
        if (file != null) {
            String today = df.format(new Date());
            File folder = new File(uploadPath + "/" + today);
            if (!folder.exists()) {
                boolean flag = folder.mkdirs();
                log.info(String.valueOf(flag));
            }
            String oldFileName = file.getOriginalFilename();
            assert oldFileName != null;
            int index = oldFileName.lastIndexOf(".");
            String suffixName = oldFileName.substring(index);
            log.info("⽂件后缀名： {}", suffixName);
            String newFileName = UUID.randomUUID() + suffixName;
            log.info(" 新的⽂件名： {}", newFileName);

           try {
                file.transferTo(new File(folder, newFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
           return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + today + "/" + newFileName;
        } else {
            return "⽂件不能为空";
        }
    }


    @PostMapping("/minio")
    public String minioUpload(MultipartFile file) {
        if (file != null) {
            String url;
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                url = minIoTemplate.putObject("test", "img/" + UUID.randomUUID() + suffixName, file.getInputStream());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return url;
        } else {
            return "⽂件不能为空";
        }
    }
    @PostMapping("/oss")
    public String ossUpload(MultipartFile file) {
        return ossTemplate.ossUpload(file);
    }
}
