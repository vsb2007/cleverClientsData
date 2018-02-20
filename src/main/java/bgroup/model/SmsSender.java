package bgroup.model;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppBindException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by VSB on 30.01.2017.
 * medicalpartners
 */
@Repository("smsSender")
public class SmsSender {
    static Logger logger = LoggerFactory.getLogger(SmsSender.class);
    Properties properties = new Properties();
    static String propsFilePath = "smppsender.cfg";

    /**
     * Address of the SMSC.
     */
    String ipAddress = null;

    /**
     * The port number to bind to on the SMSC server.
     */
    int port = 0;

    /**
     * The name which identifies you to SMSC.
     */
    String systemId = null;

    /**
     * The password for authentication to SMSC.
     */
    String password = null;
    /*
    senderIdString
     */
    String srcAddress = null;

    public SmsSender() throws IOException {
        loadProperties(propsFilePath);
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getPassword() {
        return password;
    }

    public boolean sendSms(String phoneNumber, String smsText) {
        DefaultSmppClient client = new DefaultSmppClient();

        SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();

        SmsSender smsSender = null;
        try {
            smsSender = new SmsSender();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Exception initialising SMPPSender " + e);
        }

        if (smsSender==null){
            logger.error("SMS канал не создан");
        }

        sessionConfig.setType(SmppBindType.TRANSCEIVER);
        sessionConfig.setHost(smsSender.getIpAddress());
        sessionConfig.setPort(smsSender.getPort());
        // T-Service
        //sessionConfig.setSystemId("sm717919474");
        //sessionConfig.setPassword("qiSNNDQ3");
        // EUROMED
        sessionConfig.setSystemId(smsSender.getSystemId());
        sessionConfig.setPassword(smsSender.getPassword());

        String srcSender = smsSender.getSrcAddress();

        try {
            SmppSession session = client.bind(sessionConfig);

            SubmitSm sm = createSubmitSm(srcSender, phoneNumber, smsText, "UCS-2");

            logger.debug("Try to send message");

            session.submit(sm, TimeUnit.SECONDS.toMillis(60));

            logger.debug("Message sent");

            logger.debug("Wait 10 seconds");

            TimeUnit.SECONDS.sleep(10);

            logger.debug("Destroy session");

            session.close();
            session.destroy();

            logger.debug("Destroy sms client");

            client.destroy();

            logger.debug("Bye sms!");
        } catch (SmppTimeoutException ex) {
            logger.error(ex.toString());
            return false;
        } catch (SmppChannelException ex) {
            logger.error(ex.toString());
            return false;
        } catch (SmppBindException ex) {
            logger.error(ex.toString());
            return false;
        } catch (UnrecoverablePduException ex) {
            logger.error(ex.toString());
            return false;
        } catch (InterruptedException ex) {
            logger.error(ex.toString());
            return false;
        } catch (RecoverablePduException ex) {
            logger.error(ex.toString());
            return false;
        }
        return true;
    }

    private static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();

        // For alpha numeric will use
        // TON=5
        // NPI=0
        sm.setSourceAddress(new Address((byte)5, (byte)0, src));

        // For national numbers will use
        // TON=1
        // NPI=1
        sm.setDestAddress(new Address((byte)1, (byte)1, dst));

        // Set datacoding to UCS-2
        sm.setDataCoding((byte)8);

        // Encode text
        sm.setShortMessage(CharsetUtil.encode(text, charset));

        return sm;
    }
    /**
     * Gets a property and converts it into byte.
     */
    private byte getByteProperty(String propName, byte defaultValue) {
        return Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)));
    }

    /**
     * Gets a property and converts it into integer.
     */
    private int getIntProperty(String propName, int defaultValue) {
        return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)));
    }


    private void loadProperties(String fileName) throws IOException {
        logger.debug("Reading configuration file " + fileName + "...");

        InputStream propsFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
        //FileInputStream propsFile = new FileInputStream(fileName);

        properties.load(propsFile);
        propsFile.close();
        logger.debug("Setting default parameters...");

        ipAddress = properties.getProperty("ip-address");
        port = getIntProperty("port", port);
        systemId = properties.getProperty("system-id");
        password = properties.getProperty("password");
        srcAddress = properties.getProperty("source-address");
    }
}
