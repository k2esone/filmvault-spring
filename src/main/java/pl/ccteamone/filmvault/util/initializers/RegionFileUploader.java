package pl.ccteamone.filmvault.util.initializers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.region.dto.FileRegionDto;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.region.mapper.RegionMapper;
import pl.ccteamone.filmvault.region.service.RegionService;
import pl.ccteamone.filmvault.util.DataInitializer;

import java.io.*;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionFileUploader implements DataInitializer {
    private final RegionService regionService;
    private final RegionMapper regionMapper;
    private static final String JSON_FILE_PATH = "src/main/resources/imports/regions.json";


    public void initializeRegionsJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File regionJson = new File(JSON_FILE_PATH);
            List<RegionDto> regions = regionMapper.mapToRegionDtoList(mapper.readValue(regionJson, new TypeReference<List<FileRegionDto>>() {
            }));
            for (RegionDto region : regions) {
                if (!regionService.doesRegionExistsByCountryCode(region.getCountryCode())) {
                    if(region.getFlag() == null) {
                        region.setFlag("None");
                    }
                    regionService.createRegion(region);
                }
            }
        } catch (IOException e) {
            log.warn("Unable to find json");
        }

    }

    @Override
    public void initializeData() {
        initializeRegionsJsonFile();

    }
}
