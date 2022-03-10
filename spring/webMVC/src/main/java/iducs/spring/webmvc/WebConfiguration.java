package iducs.spring.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //annotation 호출? 정의? 컴파일러에게 전달하는 주석
@EnableWebMvc //component scan spring 이 자동으로 불러오는 객체를 생성
@ComponentScan //aop, dependency injection 구성 요소 간 결합도를 낮추고 -> 프레임워크가 결합을 제어 낮춘다는 의미는 프로그래머의 일이 적다는 뜻
//aop - 로깅, 트랜잭션, 보안 등 여러 모듈에서 공통적으로 사용하는 기능을 분리해서 관리

public class WebConfiguration implements WebMvcConfigurer {

}
