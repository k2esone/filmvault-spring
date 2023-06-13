package pl.ccteamone.filmvault.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.region.service.RegionService;

import java.io.*;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionFileUploader implements CommandLineRunner {
    private final RegionService regionService;

    private static final String CSV_FILE_PATH = "src/main/resources/imports/region_data.csv";
    private static final String JSON_FILE_PATH = "src/main/resources/imports/regions.json";
    private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_NAME_INDEX = 1;
    private static final int COUNTRY_FLAG_INDEX = 2;

    //CSV: CountryCode;Country;Flag(optional)

    public void initializeRegionsFromCSV() {
        File csvFile = new File(CSV_FILE_PATH);
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine();
            while (line != null) {
                String[] data = line.split(";");
                if (data.length < 2) {
                    continue;
                }
                if (!regionService.doesRegionExistsByCountryCode(data[COUNTRY_CODE_INDEX])) {
                    RegionDto region = RegionDto.builder()
                            .countryCode(data[COUNTRY_CODE_INDEX])
                            .country(data[COUNTRY_NAME_INDEX])
                            .build();

                    if (data.length > 2) {
                        region.setFlag(data[COUNTRY_FLAG_INDEX]);
                    } else {
                        region.setFlag("None");
                    }
                    regionService.createRegion(region);
                }

                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to find region_data.csv file");

        } catch (IOException e) {
            throw new RuntimeException("Unable to read data file");
        }
    }

    public void initializeRegionsFromJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File regionJson = new File(JSON_FILE_PATH);
            List<RegionDto> regions = mapper.readValue(regionJson, new TypeReference<List<RegionDto>>() {
            });
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
    public void run(String... args) throws Exception {
        //initializeRegionsFromCSV();
        initializeRegionsFromJsonFile();
    }
}
