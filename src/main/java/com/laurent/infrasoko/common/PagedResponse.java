package com.laurent.infrasoko.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class PagedResponse<T> extends GenericResponse<List<T>> {
    private Meta meta;

    private PagedResponse(List<T> data, HttpStatus status, String message, List<String> errors, Meta meta) {
        super(data, status, message, errors);
        this.meta = meta;
    }

    public static class Builder<T> {
        private List<T> data;
        private HttpStatus status;
        private String message;
        private List<String> errors;
        private Meta meta;

        public Builder<T> data(Page<T> page) {
            this.data = page.getContent();

            // Populate pagination details
            Meta meta = new Meta();
            Meta.Pagination pagination = new Meta.Pagination();
            pagination.setTotalElements(page.getTotalElements());
            pagination.setTotalPages(page.getTotalPages());
            pagination.setLast(page.isLast());
            pagination.setSize(page.getSize());
            pagination.setNumber(page.getNumber());
            pagination.setFirst(page.isFirst());

            meta.setPagination(pagination);
            this.meta = meta;

            return this;
        }

        public Builder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public PagedResponse<T> build() {
            if (status == null) {
                status = HttpStatus.OK; // Default status
            }
            return new PagedResponse<>(data, status, message, errors, meta);
        }
    }

    @Getter @Setter
    public static class Meta {
        private Pagination pagination;

        @Getter @Setter
        public static class Pagination {
            private long totalElements;
            private int totalPages;
            private boolean last;
            private int size;
            private int number;
            private boolean first;
        }
    }
}

