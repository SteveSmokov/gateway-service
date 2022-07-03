package com.example.gatewayservice.configs;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ToString
@ConfigurationProperties(prefix = "urls")
public class GatewayUrlsProps {
    private List<Url> unauthorized;

    public GatewayUrlsProps() {
    }

    public GatewayUrlsProps(List<Url> unauthorized) {
        this.unauthorized = unauthorized;
    }

    public List<Url> getUnauthorized() {
        return unauthorized;
    }

    public void setUnauthorized(List<Url> unauthorized) {
        this.unauthorized = unauthorized;
    }


    @Override
    public String toString() {
        return "GatewayUrlsProps{" +
                "unauthorized=" + unauthorized +
                '}';
    }

    public static class Url {
        private String path;
        private List<String> methods;

        public Url() {
        }

        public Url(String path, List<String> methods) {
            this.path = path;
            this.methods = methods;
        }

        public List<String> getMethods() {
            return methods;
        }

        public void setMethods(List<String> methods) {
            this.methods = methods;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "Url{" +
                    "path='" + path + '\'' +
                    ", methods=" + methods +
                    '}';
        }
    }
}
