package controller.util;

import bean.Device;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.service.UADetectorServiceFactory;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class DeviceUtil {

    private static final DeviceUtil instance = new DeviceUtil();

    public static DeviceUtil getInstance() {
        return instance;
    }

    private DeviceUtil() {
    }

    private static ReadableUserAgent getUserAgent() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(httpServletRequest.getHeader("User-Agent"));
        return agent;
    }

    public static Device getDevice() {
        ReadableUserAgent ag = getUserAgent();
        Device device = new Device();
        device.setBrowser(ag.getFamily().getName());
        device.setOperatingSystem(ag.getOperatingSystem().getName());
        device.setDeviceCategorie(ag.getDeviceCategory().getName());
        device.setAdresseMac(GetAddress("mac"));
        System.out.println("Device :: family :: " + ag.getFamily().getName());
        System.out.println("Operating système :: " + ag.getOperatingSystem().getName());
        System.out.println("Device cat :: " + ag.getDeviceCategory().getName());
        System.out.println("Systeme version number :: " + ag.getOperatingSystem().getVersionNumber());
        System.out.println("Device Address MAC :: " + GetAddress("mac"));
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        System.out.println("Header :: "+userAgent);
        return device;
    }
    
    
    /**
    Methode : to Obtain Address MAC and IP
    Author : Youssef Benihoud
     * @param addressType : "mac" or "ip"
     * @return IP ; MAC
    */
    
    public static String GetAddress(String addressType) {
        String address = "";
        InetAddress lanIp = null;
        try {

            String ipAddress = null;
            Enumeration<NetworkInterface> net = null;
            net = NetworkInterface.getNetworkInterfaces();

            while (net.hasMoreElements()) {
                NetworkInterface element = net.nextElement();
                Enumeration<InetAddress> addresses = element.getInetAddresses();

                while (addresses.hasMoreElements() && !isVMMac(element.getHardwareAddress())) {
                    InetAddress ip = addresses.nextElement();
                    if (ip instanceof Inet4Address) {

                        if (ip.isSiteLocalAddress()) {
                            ipAddress = ip.getHostAddress();
                            lanIp = InetAddress.getByName(ipAddress);
                        }

                    }

                }
            }

            if (lanIp == null)
                return null;

            if (addressType.equals("ip")) {

                address = lanIp.toString().replaceAll("^/+", "");

            } else if (addressType.equals("mac")) {

                address = getMacAddress(lanIp);

            } else {

                throw new Exception("Specify \"ip\" or \"mac\"");

            }

        } catch (UnknownHostException ex) {

            ex.printStackTrace();

        } catch (SocketException ex) {

            ex.printStackTrace();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return address;

    }

    private static String getMacAddress(InetAddress ip) {
        String address = null;
        try {

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            address = sb.toString();

        } catch (SocketException ex) {

            ex.printStackTrace();

        }

        return address;
    }

    private static boolean isVMMac(byte[] mac) {
        if(null == mac) return false;
        byte invalidMacs[][] = {
                {0x00, 0x05, 0x69},             //VMWare
                {0x00, 0x1C, 0x14},             //VMWare
                {0x00, 0x0C, 0x29},             //VMWare
                {0x00, 0x50, 0x56},             //VMWare
                {0x08, 0x00, 0x27},             //Virtualbox
                {0x0A, 0x00, 0x27},             //Virtualbox
                {0x00, 0x03, (byte)0xFF},       //Virtual-PC
                {0x00, 0x15, 0x5D}              //Hyper-V
        };

        for (byte[] invalid: invalidMacs){
            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) return true;
        }

        return false;
    }

    
    /*
    Methode : to Obtain Address MAC
    Author : Youssef Benihoud
    */

}
