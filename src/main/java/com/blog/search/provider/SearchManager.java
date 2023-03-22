package com.blog.search.provider;

import com.blog.core.GenericProviderManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchManager extends GenericProviderManager<SearchType, SearchProvider> {
    public SearchManager(List<SearchProvider> providers) {
        super(providers);
    }
}
