package ru.softlab.zikova.efr.processes;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.softlab.efr.test.common.bpm.BpmProcessTestBase;
import ru.softlab.zikova.efr.config.TestProcessConfiguration;


/**
 * @author niculichev
 * @since 16.10.2017
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestProcessConfiguration.class})
public class SimpleTaskTest extends BpmProcessTestBase {
    private static final String CREATE_CLIENT_PROCESS_RESOURCE = "processes/testClient.bpmn20.xml";
    private static final String START_EVENT_ID = "StartEventTest";
    private static final String PROCESS_ID = "TestProcess";

    @Test
    @Deployment(resources = CREATE_CLIENT_PROCESS_RESOURCE)
    public void canStartProcess() throws Exception {
        ProcessInstance processInstance = createProcessInstanceByKey(PROCESS_ID)
                .startAfterActivity(START_EVENT_ID)
                .execute();
        assertAfterAsyncJobsThat(processInstance)
                .isWaitingAt("SimpleUserTask");
    }
}
