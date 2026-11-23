package br.edu.ifg.labguide_professor.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        // Substitua pelos dados do seu Dashboard do Cloudinary
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "SEU_CLOUD_NAME",
                "api_key", "SUA_API_KEY",
                "api_secret", "SEU_API_SECRET",
                "secure", true
        ));
    }

    public String uploadImagem(MultipartFile arquivo) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                arquivo.getBytes(),
                ObjectUtils.asMap("folder", "labguide_evidencias")
        );
        // Retorna a URL HTTPS pública da imagem hospedada
        return uploadResult.get("secure_url").toString();
    }
}