package top.shy.springboot.configure.util;

import com.aliyun.oss.OSS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.shy.springboot.configure.config.OssConfig;

import java.io.InputStream;
import java.util.UUID;

@Component
public class OssUtil {
    @Resource
    private OSS ossClient;
    @Resource
    private OssConfig ossConfig;

    public String uploadFile(MultipartFile file){
        try{
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null){
                throw new IllegalArgumentException("文件名称不能为空");
            }
            String filename = UUID.randomUUID().toString() + "_" + originalFilename;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(ossConfig.getBucketName(),filename,inputStream);
            return "https://" + ossConfig.getBucketName() + "," + ossConfig.getEndpoint().replace("https://","") + "/" + filename;
        }catch (Exception e){
            throw new RuntimeException("文件上传失败",e);
        }
    }
}
