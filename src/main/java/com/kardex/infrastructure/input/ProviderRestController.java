package com.kardex.infrastructure.input;

import com.kardex.application.dto.providerDto.ProviderNameResponse;
import com.kardex.application.dto.providerDto.ProviderPaginated;
import com.kardex.application.dto.providerDto.ProviderRequest;
import com.kardex.application.dto.providerDto.ProviderResponse;
import com.kardex.application.handler.providerHandler.IProviderHandler;
import com.kardex.domain.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(Constants.PROVIDERS_BASE_PATH)
@RequiredArgsConstructor
public class ProviderRestController {

    private final IProviderHandler providerHandler;

    @Operation(summary = "Add a new provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.CREATED, description = "Provider created", content = @Content),
            @ApiResponse(responseCode = Constants.CONFLICT, description = "Provider already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveProvider(@Valid @ModelAttribute ProviderRequest providerRequest, @RequestParam("image") MultipartFile image) {
        providerHandler.saveProvider(providerRequest, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get provider name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Providers found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Provider not found", content = @Content)
    })
    @GetMapping(Constants.GET_PROVIDER_NAME_PATH)
    public ResponseEntity<List<ProviderNameResponse>> getProviderName(){
        return ResponseEntity.ok(providerHandler.getProviderName());
    }

    @Operation(summary = "Get a provider by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Provider found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Provider not found", content = @Content)
    })
    @GetMapping(Constants.PROVIDER_ID_PATH)
    public ResponseEntity<ProviderResponse> getProvider(@Parameter(description = "ID of the provider to be returned")
                                                      @PathVariable(name = "providerId") Long providerId) {
        return ResponseEntity.ok(providerHandler.getProvider(providerId));
    }

    @Operation(summary = "Get paginated list of providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Paged providers returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<ProviderPaginated>> getProviders(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_ASC) boolean asc) {
        Page<ProviderPaginated> providers = providerHandler.getAllProviders(page, size, sortBy, asc);
        return ResponseEntity.ok(providers);
    }
}
