package se.yrgo.services.calls;

import java.util.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;
import se.yrgo.services.customers.*;
import se.yrgo.services.diary.*;

public class CallHandlingServiceImpl implements CallHandlingService {
    private CustomerManagementService customerService;
    private DiaryManagementService diaryService;

    public CallHandlingServiceImpl(CustomerManagementService customerService, DiaryManagementService diaryService) {
        this.customerService = customerService;
        this.diaryService = diaryService;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions)
            throws CustomerNotFoundException, RecordNotFoundException {

        customerService.recordCall(customerId, newCall);

        actions.forEach(diaryService::recordAction);
    }

}
