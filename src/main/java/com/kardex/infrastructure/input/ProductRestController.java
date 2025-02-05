package com.kardex.infrastructure.input;

import com.kardex.application.dto.productDto.ProductPaginated;
import com.kardex.application.dto.productDto.ProductRequest;
import com.kardex.application.dto.productDto.ProductResponse;
import com.kardex.application.dto.productDto.ProductUpdateRequest;
import com.kardex.application.handler.productHandler.IProductHandler;
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
@RestController
@RequestMapping(Constants.PRODUCTS_BASE_PATH)
@RequiredArgsConstructor
public class ProductRestController {
    private final IProductHandler productHandler;

    @Operation(summary = "Add a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.CREATED, description = "Product created", content = @Content),
            @ApiResponse(responseCode = Constants.CONFLICT, description = "Product already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveProduct(@Valid @ModelAttribute ProductRequest productRequest, @RequestParam("image") MultipartFile image) {
        productHandler.saveProduct(productRequest, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Product found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Product not found", content = @Content)
    })
    @GetMapping(Constants.PRODUCT_ID_PATH)
    public ResponseEntity<ProductResponse> getProduct(@Parameter(description = "ID of the product to be returned")
                                                      @PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(productHandler.getProduct(productId));
    }

    @Operation(summary = "Get paginated list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Paged products returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<ProductPaginated>> getProducts(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_ASC) boolean asc) {
        Page<ProductPaginated> products = productHandler.getAllProducts(page, size, sortBy, asc);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Product updated successfully", content = @Content),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Product not found", content = @Content)
    })
    @PutMapping(Constants.PRODUCT_ID_PATH) // Usamos la constante para el ID path
    public ResponseEntity<Void> updateProduct(@Parameter(description = "ID of the product to be updated")
                                              @PathVariable(name = "productId") Long productId,
                                              @ModelAttribute ProductUpdateRequest productRequest) {
        productHandler.updateProduct(productId, productRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Product deleted successfully", content = @Content),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Product not found", content = @Content)
    })
    @DeleteMapping(Constants.PRODUCT_ID_PATH) // Usamos la constante para el ID path
    public ResponseEntity<Void> deleteProductFromStock(@Parameter(description = "ID of the product to be deleted")
                                                       @PathVariable(name = "productId") Long productId) {
        productHandler.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
