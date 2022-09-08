package io.michel.urlshortener.controller.v1.api;

import io.michel.urlshortener.dto.model.url.UrlDto;
import io.michel.urlshortener.service.UrlService;
import io.michel.urlshortener.util.DateUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static io.michel.urlshortener.exception.BRSException.throwException;

@RestController
public class RedirectController {
    final UrlService urlService;

    public RedirectController(final UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{slug}")
    public ModelAndView redirectToMappedUrl(ModelMap model, @PathVariable String slug) {
        UrlDto urlDto = urlService.getUrlMapping(slug);
        if (urlDto.getExpirationDate().before(DateUtils.today())) {
            throw throwException("This link has expired.");
        }

        return new ModelAndView(String.format("redirect:%s", urlDto.getOriginalUrl()), model);
    }
}
