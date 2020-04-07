package ru.kokovin.votesystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.kokovin.votesystem.model.User;
import ru.kokovin.votesystem.repository.DishRepository;
import ru.kokovin.votesystem.repository.RestaurantRepository;
import ru.kokovin.votesystem.repository.UserRepository;
import ru.kokovin.votesystem.repository.VoteRepository;
import ru.kokovin.votesystem.service.MyUserDetailService;
import ru.kokovin.votesystem.service.VoteService;
import ru.kokovin.votesystem.util.JsonUtil;

import javax.annotation.PostConstruct;

import java.time.Clock;
import java.time.ZoneId;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static ru.kokovin.votesystem.AbstractControllerTest.RequestWrapper.wrap;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MyUserDetailService userService;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected DishRepository dishRepository;

    @Autowired
    protected Clock clock;

    @Autowired
    protected ZoneId zoneId;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String url;

    public AbstractControllerTest(String url) {
        this.url = url + '/';
    }

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(springSecurity())
                .build();
    }

    public ResultActions perform(RequestWrapper wrapper) throws Exception {
        return perform(wrapper.builder);
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    protected RequestWrapper doGet(String urlTemplatePad, Object... uriVars) {
        return wrap(MockMvcRequestBuilders.get(url + urlTemplatePad, uriVars));
    }

    protected RequestWrapper doGet() {
        return wrap(MockMvcRequestBuilders.get(url));
    }

    protected RequestWrapper doGet(String pad) {
        return wrap(MockMvcRequestBuilders.get(url + pad));
    }

    protected RequestWrapper doGet(int id) {
        return doGet("{id}", id);
    }

    protected RequestWrapper doDelete() {
        return wrap(MockMvcRequestBuilders.delete(url));
    }

    protected RequestWrapper doDelete(int id) {
        return wrap(MockMvcRequestBuilders.delete(url + "{id}", id));
    }

    protected RequestWrapper doPut() {
        return wrap(MockMvcRequestBuilders.put(url));
    }

    protected RequestWrapper doPut(int id) {
        return wrap(MockMvcRequestBuilders.put(url + "{id}", id));
    }

    protected RequestWrapper doPost(String pad) throws Exception {
        return wrap(MockMvcRequestBuilders.post(url + pad));
    }

    protected RequestWrapper doPost() {
        return wrap(MockMvcRequestBuilders.post(url));
    }

    protected RequestWrapper doPatch(int id) {
        return wrap(MockMvcRequestBuilders.patch(url + "{id}", id));
    }

    public static class RequestWrapper {
        private final MockHttpServletRequestBuilder builder;

        private RequestWrapper(MockHttpServletRequestBuilder builder) {
            this.builder = builder;
        }

        public static RequestWrapper wrap(MockHttpServletRequestBuilder builder) {
            return new RequestWrapper(builder);
        }

        public MockHttpServletRequestBuilder unwrap() {
            return builder;
        }

        public <T> RequestWrapper jsonBody(T body) {
            builder.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(body));
            return this;
        }

        public RequestWrapper jsonUserWithPassword(User user) {
            builder.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeAdditionProps(user, "password", user.getPassword()));
            return this;
        }

        public RequestWrapper basicAuth(User user) {
            builder.with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword()));
            return this;
        }

        public RequestWrapper auth(User user) {
            builder.with(SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())));
            return this;
        }
    }

}
