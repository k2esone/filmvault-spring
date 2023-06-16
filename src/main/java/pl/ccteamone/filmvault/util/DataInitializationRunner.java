package pl.ccteamone.filmvault.util;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializationRunner implements CommandLineRunner {
    private final List<DataInitializer> dataInitializers;

    public DataInitializationRunner(@Qualifier("genreFileUploader") DataInitializer genresInitializer,
                                    @Qualifier("platformFileUploader") DataInitializer vodPlatformsInitializer,
                                    @Qualifier("regionFileUploader") DataInitializer regionsInitializer) {
        this.dataInitializers = Arrays.asList(genresInitializer, vodPlatformsInitializer, regionsInitializer);
    }

    @Override
    public void run(String... args) throws Exception {
        for (DataInitializer dataInitializer : dataInitializers) {
            dataInitializer.initializeData();
        }

    }
}
