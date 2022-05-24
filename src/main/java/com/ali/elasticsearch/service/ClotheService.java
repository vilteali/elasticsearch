package com.ali.elasticsearch.service;

import com.ali.elasticsearch.utils.JsonAdapter;
import com.ali.elasticsearch.domain.Clothe;
import com.ali.elasticsearch.dto.ClotheDto;
import com.ali.elasticsearch.repository.ClotheRepository;
import com.ali.elasticsearch.repository.GenericElasticsearchRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClotheService extends ClientElasticService<ClotheDto, Clothe> {

    private final ClotheRepository clotheRepository;

    @Autowired
    public ClotheService(ElasticsearchOperations elasticsearchOperations,
                         RestHighLevelClient client,
                         JsonAdapter adapter,
                         ClotheRepository clotheRepository) {
        super(elasticsearchOperations, client, adapter);
        this.clotheRepository = clotheRepository;
    }

    @Override
    protected Map<String, Object> createParametersFrom(ClotheDto dto) {
        return null;
    }

    @Override
    protected Clothe elementForSaveFrom(ClotheDto clotheDto) {
        return Clothe.forSave(clotheDto);
    }

    @Override
    protected Clothe elementForUpdateFrom(ClotheDto clotheDto) {
        return null;
    }

    @Override
    protected String index() {
        return Clothe.INDEX;
    }

    @Override
    protected GenericElasticsearchRepository<Clothe> repository() {
        return clotheRepository;
    }

    @Override
    protected Class<Clothe> entity() {
        return Clothe.class;
    }

    public ClotheDto findByCode(UUID code) {
        return clotheRepository.findByCode(code)
                .map(ClotheDto::from)
                .orElseThrow(RuntimeException::new);    // a custom exception is better
    }

    public List<ClotheDto> findAllAsDto() {
        List<Clothe> clothes = findAll();

        return mapToDtoAndCollectAsList(clothes);
    }

    public void deleteByCode(UUID code) {
        clotheRepository.deleteByCode(code);
    }

    public List<ClotheDto> findAllBySize(String size) {
        List<Clothe> clothes = clotheRepository.findAllBySize(size);

        return mapToDtoAndCollectAsList(clothes);
    }

    public List<ClotheDto> findAllByName(String name) {
        List<Clothe> clothes = clotheRepository.findAllByNameContainingIgnoreCase(name);

        return mapToDtoAndCollectAsList(clothes);
    }

    private List<ClotheDto> mapToDtoAndCollectAsList(List<Clothe> clothes) {
        return clothes.stream()
                .map(ClotheDto::from)
                .collect(Collectors.toList());
    }
}
