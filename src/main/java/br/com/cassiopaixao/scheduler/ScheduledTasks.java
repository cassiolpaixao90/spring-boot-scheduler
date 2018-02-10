package br.com.cassiopaixao.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author CassioPaixao
 *
 */

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	/*
	 * Executado em um intervalo fixo a cada 2 segundos
	 */
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		logger.info("Tarefa de taxa fixa :: Tempo de execução - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

	/*
	 * executar uma tarefa com um atraso fixo entre a conclusão da última invocação
	 * e o início do próximo
	 */
	@Scheduled(fixedDelay = 2000)
	public void scheduleTaskWithFixedDelay() {
		logger.info("Tarefa com Delay :: Tempo de execução - {}", dateTimeFormatter.format(LocalDateTime.now()));
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ex) {
			logger.error("Ocorreu em um erro {}", ex);
			throw new IllegalStateException(ex);
		}
	}

	/*
	 * execução da tarefa será atrasada em 5 segundos e então será executada
	 * normalmente em um intervalo fixo de 2 segundos
	 */
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	public void scheduleTaskWithInitialDelay() {
		logger.info("Tarefa taxa fixa com atraso inicial :: Tempo de execução - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
	}

	/*
	 * Agendar a execução de suas tarefas - por exemplo a cada minuto
	 */
	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() {
		logger.info("Tarefa Cron :: Tempo de execução - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}
