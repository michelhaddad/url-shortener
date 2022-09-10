package io.michel.urlshortener.controller.v1.api;

import io.michel.urlshortener.dto.model.url.UrlDto;
import io.michel.urlshortener.dto.response.Response;
import io.michel.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/url")
public class UrlController {
    UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public Response<UrlDto> addUrlMapping(@RequestBody UrlDto urlDtoRequest) {
        UrlDto url = urlService.addUrlMapping(urlDtoRequest);
        return Response.<UrlDto>ok().setPayload(url);
    }

    @GetMapping("/{hash}")
    public Response<UrlDto> getUrlMapping(@PathVariable String hash) {
        UrlDto url = urlService.getUrlMapping(hash);
        return Response.<UrlDto>ok().setPayload(url);
    }

    @DeleteMapping("/{hash}")
    public Response<Void> deleteUrlMapping(@PathVariable String hash) {
        urlService.deleteUrlMapping(hash);
        return Response.ok();
    }
}
