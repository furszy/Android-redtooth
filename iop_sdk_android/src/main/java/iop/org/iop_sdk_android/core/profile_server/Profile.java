package iop.org.iop_sdk_android.core.profile_server;


import iop.org.iop_sdk_android.core.crypto.CryptoImp;
import iop.org.iop_sdk_android.core.crypto.KeyEd25519;
import iop_sdk.profile_server.Signer;

/**
 * Created by mati on 09/11/16.
 */

public class Profile implements Signer {

    /** Key del profile */
    private KeyEd25519 keyEd25519;

    /** Random 32 bytes number created by the client and sended to the server in the StartConversationRequest message */
    private byte[] connectionChallenge;
    /** Signed client challenge from the server  */
    private byte[] signedConnectionChallenge;
    /** Random 32 bytes number created by the node and obtained by the client in the StartConversationResponse */
    private byte[] nodeChallenge;

    // specific fields
    private byte[] version;
    private String name;
    private byte[] img;
    private int latitude;
    private int longitude;
    private String extraData;
    private byte[] nodePubKey;


    public Profile(byte[] version,String name) {
        this.version = version;
        this.name = name;
        this.keyEd25519 = KeyEd25519.generateKeys();

        initRandomNumbers();
    }

    public Profile(byte[] protocolVersion, String username, KeyEd25519 keyEd25519) {
        this.version = protocolVersion;
        this.name = username;
        this.keyEd25519 = keyEd25519;

        initRandomNumbers();
    }

    private void initRandomNumbers(){
        // random number
        //connectionChallenge = KeyEd25519.generateKeys().getPublicKey();
        connectionChallenge = new byte[32];
        CryptoImp.random(connectionChallenge,32);
    }

    public void setConnectionChallenge(byte[] connectionChallenge) {
        this.connectionChallenge = connectionChallenge;
    }

    public void setSignedConnectionChallenge(byte[] signedConnectionChallenge) {
        this.signedConnectionChallenge = signedConnectionChallenge;
    }


    public void setNodeChallenge(byte[] nodeChallenge) {
        this.nodeChallenge = nodeChallenge;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public byte[] getPublicKey() {
        return keyEd25519.getPublicKey();
    }

    public String getHexPublicKey() {
        return keyEd25519.getPublicKeyHex();
    }

    public byte[] getPrivKey() {
        return keyEd25519.getPrivateKey();
    }

    public String getPrivKeyHex() {
        return keyEd25519.getPrivateKeyHex();
    }

    public byte[] getSignedConnectionChallenge() {
        return signedConnectionChallenge;
    }

    public byte[] getConnectionChallenge() {
        return connectionChallenge;
    }

    public byte[] getNodeChallenge() {
        return nodeChallenge;
    }

    public byte[] getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public byte[] getImg() {
        return img;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public String getExtraData() {
        return extraData;
    }

    @Override
    public byte[] sign(byte[] message) {
        return KeyEd25519.sign(message,keyEd25519.getExpandedPrivateKey());
    }

    @Override
    public boolean verify(byte[] signature,byte[] message) {
        return KeyEd25519.verify(signature,message,keyEd25519.getPublicKey());
    }

    public Object getKey() {
        return keyEd25519;
    }

    public byte[] getNodePubKey() {
        return nodePubKey;
    }

    public void setNodePubKey(byte[] nodePubKey) {
        this.nodePubKey = nodePubKey;
    }
}
