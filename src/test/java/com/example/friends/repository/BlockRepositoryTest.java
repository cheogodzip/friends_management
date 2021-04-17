package com.example.friends.repository;

import com.example.friends.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block();
        block.setName("martin");
        block.setReason("친하지 않아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);

        List<Block> blockList = blockRepository.findAll();

        assertThat(blockList.size()).isEqualTo(3);
        assertThat(blockList.get(0).getName()).isEqualTo("dennis");
        assertThat(blockList.get(1).getName()).isEqualTo("sophia");
        assertThat(blockList.get(2).getName()).isEqualTo("martin");
    }
}