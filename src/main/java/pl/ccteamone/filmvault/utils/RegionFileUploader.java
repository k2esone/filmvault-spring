package pl.ccteamone.filmvault.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.ccteamone.filmvault.region.dto.RegionDto;
import pl.ccteamone.filmvault.region.service.RegionService;

import java.io.*;
@Component
@RequiredArgsConstructor
public class RegionFileUploader implements CommandLineRunner {
    private final RegionService regionService;

    private static final String FILE_PATH = "src/main/resources/imports/region_data.csv";
    private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_NAME_INDEX = 1;
    private static final int COUNTRY_FLAG_INDEX = 2;
    //CSV: CountryCode;Country;Flag

    public void initializeRegionsFromCSV() {
        File csvFile = new File(FILE_PATH);
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine();
            while (line != null) {
                String[] data = line.split(";");
                if (!regionService.doesRegionExistsByCountryCode(data[COUNTRY_CODE_INDEX])) {
                    regionService.createRegion(RegionDto.builder()
                            .countryCode(data[COUNTRY_CODE_INDEX])
                            .country(data[COUNTRY_NAME_INDEX])
                            .flag(data[COUNTRY_FLAG_INDEX])
                            .build());
                }

                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to find region_data.csv file");

        } catch (IOException e) {
            throw new RuntimeException("Unable to read data file");
        }


    }

    @Override
    public void run(String... args) throws Exception {
        initializeRegionsFromCSV();
    }
}
