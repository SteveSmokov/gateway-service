package com.example.gatewayservice.configs;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Data
@ToString
@ConfigurationProperties(prefix = "gateway")
public class GatewayUrlsProps {
    private List<Url> urls;

    public GatewayUrlsProps() {
    }

    public GatewayUrlsProps(List<Url> urls) {
        this.urls = urls;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public boolean checkMethod(String url, HttpMethod method){
        return urls.stream().anyMatch(u -> {
            if (u.path.equals(url)) {
                return u.methods.stream().anyMatch(m -> m.equals(method));
            } else  return false;
        });
    }

    public Optional<String> getRedirectUrl(String url, HttpMethod method){
        return urls.stream().filter(u -> {
            if (u.path.equals(url)) {
                return u.methods.stream().anyMatch(m -> m.equals(method));
            }
            return false;
        }).map(url1 -> url1.redirectUrl).findFirst();
    }

    public static class Url {
        private String path;
        private Set<HttpMethod> methods;
        private String redirectUrl;

        public Url() {
        }

        public Url(String path, Set<HttpMethod> methods, String redirectUrl) {
            this.path = path;
            this.methods = methods;
            this.redirectUrl = redirectUrl;
        }

        public Set<HttpMethod> getMethods() {
            return methods;
        }

        public void setMethods(Set<HttpMethod> methods) {
            this.methods = methods;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        @Override
        public String toString() {
            return "Url{" +
                    "path='" + path + '\'' +
                    ", methods=" + methods +
                    ", redirectUrl='" + redirectUrl + '\'' +
                    '}';
        }
    }
}
