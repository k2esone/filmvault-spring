package pl.ccteamone.filmvault.appuser.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('USER')")
    public String hello() {
        return "Hello";
    }

}
