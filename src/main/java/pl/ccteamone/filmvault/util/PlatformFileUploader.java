package pl.ccteamone.filmvault.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.vodplatform.dto.FileVODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.dto.VODPlatformDto;
import pl.ccteamone.filmvault.vodplatform.mapper.VODPlatformMapper;
import pl.ccteamone.filmvault.vodplatform.service.VODPlatformService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlatformFileUploader implements CommandLineRunner {

    private final VODPlatformService platformService;
    private final VODPlatformMapper platformMapper;

    private static final String JSON_FILE_PATH = "src/main/resources/imports/vodplatforms.json";

    public void initializeVODPlatformsJsonFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File platformJson = new File(JSON_FILE_PATH);
            List<VODPlatformDto> platforms = platformMapper.mapToVODPlatformDtoList(mapper.readValue(platformJson, new TypeReference<List<FileVODPlatformDto>>() {
            }));
            for (VODPlatformDto platform : platforms) {
                if (!platformService.existsPlatformByName(platform.getName())) {
                    if(platform.getVodURL() == null || platform.getVodURL().isEmpty()) {
                        platform.setVodURL("URL: None");
                    }
                    platformService.createVODPlatform(platform);
                }
            }

        } catch (IOException e) {
            log.warn("Unable to find json");
        }

    }

    @Override
    public void run(String... args) throws Exception {
        initializeVODPlatformsJsonFromFile();

    }
}
