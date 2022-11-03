package ru.amlet;

public class LessonExecutorService {

    private final InetAddressService inetAddressService;
    private final NetworkInterfaceService networkInterfaceService;

    public LessonExecutorService(InetAddressService inetAddressService,
                                 NetworkInterfaceService networkInterfaceService) {
        this.inetAddressService = inetAddressService;
        this.networkInterfaceService = networkInterfaceService;
    }

    public void execute() {
        inetAddressService.workWithInetAddress();
        networkInterfaceService.workWithNetworkInterface();
    }

}
