package com.vertexcover;

import com.vertexcover.api.ApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = {ApiApplication.class})
@ContextConfiguration
class AbstractTest {
}
