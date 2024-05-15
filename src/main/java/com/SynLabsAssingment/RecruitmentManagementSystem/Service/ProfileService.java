package com.SynLabsAssingment.RecruitmentManagementSystem.Service;

import com.SynLabsAssingment.RecruitmentManagementSystem.Converter.ProfileConverter;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.ProfileRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.ProfileNotFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.UserNoFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Profile;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;
import com.SynLabsAssingment.RecruitmentManagementSystem.Repository.ProfileRepository;
import com.SynLabsAssingment.RecruitmentManagementSystem.Repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProfileService {

    @Value("${resume.api.key}")
    private String apiKey;

    @Value("${resume.storage.location}")
    private String storageLocation;

    private ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createProfile(ProfileRequestDto profileDTO) throws UserNoFoundException {
        Profile profile = ProfileConverter.profileRequestDtoToProfile(profileDTO);
        User user = userRepository.findById(profileDTO.getUserId())
                .orElseThrow(() -> new UserNoFoundException("Invalid UserID"));
        profileRepository.save(profile);
    }

    public JSONObject uploadResume(MultipartFile file, int userId) throws IOException {
        String apiUrl = "https://api.apilayer.com/resume_parser/upload";
        String apiKey = "YOUR_API_KEY_HERE";

        byte[] resumeFile = file.getBytes();
        Path filePath = saveResumeFile(file);
        String fileAddress = filePath.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("apikey", apiKey);

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(resumeFile, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
            return new JSONObject(responseEntity.getBody());
            // Parse JSON response and store relevant information
            // Example: JSONObject = new JSONObject(jsonResponse);
        } else {
            // Handle error response
            return new JSONObject("Resume parsing failed. Status code: " + responseEntity.getStatusCode());
        }
//        return new JSONObject(apiUrl);
    }

    private Path saveResumeFile(MultipartFile resumeFile) throws IOException {
        Path storageDir = Paths.get(storageLocation);
        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }

        Path filePath = storageDir.resolve(resumeFile.getOriginalFilename());
        Files.write(filePath, resumeFile.getBytes());

        return filePath;
    }

    public Profile getApplicant(int applicantId) throws Exception {
        Profile profile = profileRepository.findById(applicantId)
                .orElseThrow(() -> new ProfileNotFoundException("Job not found"));
        return profile;
    }

    public List<Profile> getApplicants() {
        return profileRepository.findAll();
    }

}
