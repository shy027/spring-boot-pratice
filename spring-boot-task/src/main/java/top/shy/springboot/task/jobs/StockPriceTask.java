package top.shy.springboot.task.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.shy.springboot.task.entity.stockPrice;
import top.shy.springboot.task.mapper.StockPriceMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Random;

//@Component
@AllArgsConstructor
@Slf4j
public class StockPriceTask {
    private final StockPriceMapper stockPriceMapper;
    private final Random random = new Random();
    @Scheduled(fixedRate = 10000)
    public void updateStockPrice(){
        double price = 100 + random.nextDouble() * 50;
        BigDecimal roundedPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
        stockPrice stockPrice = new stockPrice();
        stockPrice.setPrice(roundedPrice.doubleValue());
        stockPrice.setName("小米");
        stockPrice.setUpdateTime(LocalDateTime.now());

        stockPriceMapper.insert(stockPrice);
        log.info("股票价格已更新：{},时间：{}",price,LocalDateTime.now());
    }
}
