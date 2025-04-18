    package com.kardex.infrastructure.out.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "provider")
    @Data
    @NoArgsConstructor
    public class ProviderEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "company_name", nullable = false, unique = true)
        private String companyName;

        @Column(name = "image_url", nullable = false)
        private String imageUrl;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String phone;

        @Column(nullable = false)
        private Boolean status;

        @Column(nullable = false)
        private String description;
    }
