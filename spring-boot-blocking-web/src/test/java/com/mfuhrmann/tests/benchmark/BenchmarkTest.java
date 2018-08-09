//package com.mfuhrmann.tests.benchmark;
//
//import com.google.common.base.Stopwatch;
//import com.mfuhrmann.tests.Inserter;
//import org.junit.After;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * This is a test class intended to use as a benchmark of the application, that's why the test is ignored by default.
// * <p>
// * The goal is to demonstrate how a Reactive Web approach performs in a better way under a high request load compared
// * with a classic blocking solution.
// * <p>
// * To use it, first run the application. The test assumes it'll be running on localhost:8080, but you can change the
// * constant if you want.
// * <p>
// * It's recommended that you discard the fist execution results since they could include some warming up of the server.
// */
////@Ignore
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class BenchmarkTest {
//
//    private static final int PARALLELISM = 32;
//
//    private static final String BLOCKING_URL = "http://localhost:8890";
//    private static final String REACTIVE_URL = "http://localhost:8891";
//
//    private final static Logger log = LoggerFactory.getLogger(BenchmarkTest.class);
//
//    public static final String URL_BLOCKING_MAPPING = "quotes-blocking-mapping";
//    public static final String URL_BLOCKING_FINDALL = "quotes-blocking-findAll";
//
//    public static final String URL_REACTIVE_MAPPING = "quotes-reactive-mapping";
//    public static final String URL_REACTIVE_FINDALL = "quotes-reactive-findAll";
//
//    @After
//    public void after() {
//        System.gc();
//    }
//
//    @Test
//    public void aaaThrowAwayTestToPrepareJvm() throws Exception {
//        runBenchmark(32, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark1_1RequestBlockingMapping() throws Exception {
//        runBenchmark(1, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark1_1RequestBlockingFindAll() throws Exception {
//        runBenchmark(1, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark1_1RequestReactiveMapping() throws Exception {
//        runBenchmark(1, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark1_1RequestReactiveFindAll() throws Exception {
//        runBenchmark(1, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//
//    @Test
//    public void benchmark2_8RequestBlockingMapping() throws Exception {
//        runBenchmark(8, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark2_8RequestBlockingFindAll() throws Exception {
//        runBenchmark(8, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark2_8RequestReactiveMapping() throws Exception {
//        runBenchmark(8, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark2_8RequestReactiveFindAll() throws Exception {
//        runBenchmark(8, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//
//    @Test
//    public void benchmark3_32RequestBlockingMapping() throws Exception {
//        runBenchmark(32, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark3_32RequestBlockingFindAll() throws Exception {
//        runBenchmark(32, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark3_32RequestReactiveMapping() throws Exception {
//        runBenchmark(32, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark3_32RequestReactiveFindAll() throws Exception {
//        runBenchmark(32, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//
//    @Test
//    public void benchmark4_96RequestBlockingMapping() throws Exception {
//        runBenchmark(96, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark4_96RequestBlockingFindAll() throws Exception {
//        runBenchmark(96, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark4_96RequestReactiveMapping() throws Exception {
//        runBenchmark(96, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark4_96RequestReactiveFindAll() throws Exception {
//        runBenchmark(96, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//
//    @Test
//    public void benchmark5_768RequestBlockingMapping() throws Exception {
//        runBenchmark(768, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark5_768RequestBlockingFindAll() throws Exception {
//        runBenchmark(768, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark5_768RequestReactiveMapping() throws Exception {
//        runBenchmark(768, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark5_768RequestReactiveFindAll() throws Exception {
//        runBenchmark(768, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//    @Test
//    public void benchmark6_2500RequestBlockingMapping() throws Exception {
//        runBenchmark(2500, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark6_2500RequestBlockingFindAll() throws Exception {
//        runBenchmark(2500, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark6_2500RequestReactiveMapping() throws Exception {
//        runBenchmark(2500, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark6_2500RequestReactiveFindAll() throws Exception {
//        runBenchmark(2500, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//    @Test
//    public void benchmark7_10000RequestBlockingMapping() throws Exception {
//        runBenchmark(10000, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_MAPPING);
//    }
//
//    @Test
//    public void benchmark7_10000RequestBlockingFindAll() throws Exception {
//        runBenchmark(10000, PARALLELISM, BLOCKING_URL, "/" + URL_BLOCKING_FINDALL);
//    }
//
//    @Test
//    public void benchmark7_10000RequestReactiveMapping() throws Exception {
//        runBenchmark(10000, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_MAPPING);
//    }
//
//    @Test
//    public void benchmark7_10000RequestReactiveFindAll() throws Exception {
//        runBenchmark(10000, PARALLELISM, REACTIVE_URL, "/" + URL_REACTIVE_FINDALL);
//    }
//
//    private void runBenchmark(final int requests, final int parallelism, String url, final String context) throws Exception {
//
//        Stopwatch stopwatch = Stopwatch.createStarted();
//
//
//        WebClient webClient = WebClient.create(BLOCKING_URL);
//        HashMap<Integer, BenchmarkRequestResult> results = new HashMap<>();
//
//        List<Callable<BenchmarkRequestResult>> requestCallableList = IntStream.range(0, requests)
//                .mapToObj(i -> createMonoRequest(i, webClient, context))
//                .collect(Collectors.toList());
//
//        log.info(" ========== NEW BENCHMARK --> Requests: {}, Parallelism: {}, URL_BLOCKING: {}", requests, parallelism, context);
//        log.info(" ========== Requests created ");
//
//        ExecutorService executorService = Executors.newFixedThreadPool(parallelism);
//        ExecutorCompletionService<BenchmarkRequestResult> completionService = new ExecutorCompletionService<>(executorService);
//        requestCallableList.forEach(completionService::submit);
//
//        log.info(" ========== Requests submitted @ {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
//
//        for (int n = 0; n < requestCallableList.size(); n++) {
//            BenchmarkRequestResult benchmarkRequestResult = completionService.take().get();
//            results.put(benchmarkRequestResult.getRequestId(), benchmarkRequestResult);
//        }
//
//        log.info(" ========== Requests completed @ {}", Duration.ofNanos(System.nanoTime() - stopwatch.elapsed(TimeUnit.MILLISECONDS)));
//
//        log.info(" ========== RESULTS ========== ");
//        double avg = results.values().stream().mapToLong(r -> r.getTookTimeNs()).peek(l -> log.debug("" + l)).average().getAsDouble();
//        log.info("Average time per request: {}", Duration.ofNanos(Math.round(avg)));
//        double successRate = results.values().stream().
//                filter(r -> r.getResponseEntity().getStatusCode().equals(HttpStatus.OK)).count() * 100.0 /
//                results.size();
//        double errorRate = 100.0 - successRate;
//        log.info("Success Rate: {}", successRate);
//        log.info("Error Rate:   {}", errorRate);
//        int nQuotes = results.values().stream().map(r -> r.getResponseEntity().getBody()).mapToInt(this::countQuotes).sum();
//        log.info("Total Number of Quotes: {}", nQuotes);
//
//        log.info(" ========== Benchmark took {} ", stopwatch.elapsed(TimeUnit.MILLISECONDS));
//
//        assertThat(nQuotes).isEqualTo(Inserter.DOCUMENTS_COUNT * requests);
//    }
//
//    private Callable<BenchmarkRequestResult> createMonoRequest(final int requestId, final WebClient webClient, final String url) {
//        return () -> {
//            long start = System.nanoTime();
//            ResponseEntity<String> responseEntity = webClient.get().uri(url).exchange().block().toEntity(String.class).block();
//            long end = System.nanoTime();
//            return new BenchmarkRequestResult(requestId, responseEntity, end - start);
//        };
//    }
//
//    private int countQuotes(final String s) {
//        return StringUtils.countOccurrencesOf(s, "\"id\"");
//    }
//}
