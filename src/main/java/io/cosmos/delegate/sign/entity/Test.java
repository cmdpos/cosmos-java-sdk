package io.cosmos.delegate.sign.entity;

import io.cosmos.api.CosmosTransactionImpl;
import io.cosmos.crypto.Crypto;
import io.cosmos.types.*;
import io.cosmos.util.EncodeUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        makeDelegate();
    }

    //生成交易
    public static void makeTx() {

        //base info
        byte[] priKeyVal = Hex.decode("3f92fca52e2b19c59d803da7255a26a1c1845f91cd26da2531c5971b5ed29f99");
        byte[] pubKeyVal = Hex.decode("03c1698bd1ccde2d476adfb185af90a137fa0b88c7f3716cc3a898b1c555c25d5d");


        String chainId = "cosmoshub-2";
        String memo = "memo";
        String mode ="block";
        String tranType = "auth/StdTx";

        //fee
        Token amount = new Token();
        amount.setDenom("uatom");
        amount.setAmount("1000");
        Fee fee = new Fee();
        fee.setAmount(Collections.singletonList(amount));
        fee.setGas("200000");

        //accountList
        CosmosAccount account = new CosmosAccount("cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4",
                "18435", "10");
        List<CosmosAccount> accountList = Collections.singletonList(account);

        //make signData
        CosmosSignData cosmosSignData = new CosmosSignData(chainId, fee, memo, accountList);


        //make tx.msg
        String msgType= "cosmos-sdk/MsgMultiSend";
        InputOutput input = CosmosTransactionImpl.buildInputOutput("cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4",
                "4000", "uatom");
        InputOutput output = CosmosTransactionImpl.buildInputOutput("cosmos12kxm8umypzvngtdtscaxy7zs76gpk754at6cle",
                "4000", "uatom");
        List<InputOutput> inputList = new ArrayList<>();
        inputList.add(input);
        List<InputOutput> outputList = new ArrayList<>();
        outputList.add(output);

        ValueMulti valueMulti = new ValueMulti();
        valueMulti.setInputs(inputList);
        valueMulti.setOutputs(outputList);
        MessageDelegateMulti<ValueMulti> messageDelegateMulti = new MessageDelegateMulti<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(valueMulti);
        MessageDelegateMulti[] msgs = new MessageDelegateMulti[1];
        msgs[0]=messageDelegateMulti;

        //整个json
        CosmosDelegateData cosmosDelegateData = new CosmosDelegateData();
        //signData
        cosmosDelegateData.setSignData(cosmosSignData);
        //memo
        cosmosDelegateData.setMode(mode);
        //tx
        CosmosDelegateMulti delegateMulti = new CosmosDelegateMulti();
        cosmosDelegateData.setTx(delegateMulti);
        delegateMulti.setType(tranType);
        delegateMulti.setMemo(memo);
        delegateMulti.setFee(fee);
        delegateMulti.setMsgs(msgs);
        //make sign
        List<Signature> signatures = makeSign(cosmosSignData,accountList,msgs,priKeyVal,pubKeyVal);
        delegateMulti.setSignatures(signatures);

        System.out.println( "sign transaction success!");
        System.out.println(cosmosDelegateData.toJson());
        System.out.println( "sign transaction success!");
    }


    //生成投票
    public static void makeDelegate() {
//        gaiacli tx staking delegate cosmosvaloper18thamkhnj9wz8pa4nhnp9rldprgant57pk2m8s  10000000000uatom --from cosmos12kxm8umypzvngtdtscaxy7zs76gpk754at6cle --gas auto --gas-prices 1000uatom --generate-only > unsignedTX.json
//
//        gaiacli tx sign unsignedTx.json --from-addr cosmosvaloper18thamkhnj9wz8pa4nhnp9rldprgant57pk2m8s  > signedTx.json


        //{"signData":{"chainId":"cosmoshub-2","fee":{"amount":[{"amount":"1000000","denom":"uatom"}],"gas":"200000"},"memo":"memo","accountList":[{"address":"cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4","accountNumber":"18435","sequence":"11","chainPath":"44/1044/0/0/1"}]},"mode":"block","tx":{"msg":[{"type":"cosmos-sdk/MsgDelegate","value":{"delegator_address":"cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4","validator_address":"cosmosvaloper1sjllsnramtg3ewxqwwrwjxfgc4n4ef9u2lcnj0","amount":{}}}],"fee":{"amount":[{"amount":"1000000","denom":"uatom"}],"gas":"200000"},"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"A8Fpi9HM3i1Hat+xha+QoTf6C4jH83Fsw6iYscVVwl1d"},"signature":"a2MfLouxkKNhkF6GSdg/S926LBlBi/kELGCuWCIT/Wh6KnAB7HSszATgPBktJ20j6xUMjwp44bDq0ragdj4JfA=="}],"memo":"memo","type":"auth/StdTx"}}
//        {
//            "check_tx": {
//            "code": 0,
//                    "data": "data",
//                    "log": "log",
//                    "gas_used": 5000,
//                    "gas_wanted": 10000,
//                    "info": "info",
//                    "tags": [
//            "",
//                    ""
//    ]
//        },
//            "deliver_tx": {
//            "code": 5,
//                    "data": "data",
//                    "log": "log",
//                    "gas_used": 5000,
//                    "gas_wanted": 10000,
//                    "info": "info",
//                    "tags": [
//            "",
//                    ""
//    ]
//        },
//            "hash": "EE5F3404034C524501629B56E0DDC38FAD651F04",
//                "height": 0
//        }

        //base info
        byte[] priKeyVal = Hex.decode("3f92fca52e2b19c59d803da7255a26a1c1845f91cd26da2531c5971b5ed29f99");
        byte[] pubKeyVal = Hex.decode("03c1698bd1ccde2d476adfb185af90a137fa0b88c7f3716cc3a898b1c555c25d5d");


        String chainId = "cosmoshub-2";
        String memo = "memo";
        String mode ="block";
        String tranType = "auth/StdTx";

        //fee
        Token amount = new Token();
        amount.setDenom("uatom");
        amount.setAmount("1000");
        Fee fee = new Fee();
        fee.setAmount(Collections.singletonList(amount));
        fee.setGas("200000");

        //accountList
        CosmosAccount account = new CosmosAccount("cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4",
                "18435", "16");
        List<CosmosAccount> accountList = Collections.singletonList(account);

        //make signData
        CosmosSignData cosmosSignData = new CosmosSignData(chainId, fee, memo, accountList);


        //make tx.msg
        String msgType= "cosmos-sdk/MsgDelegate";
        MsgDelegateValue delegateValue = new MsgDelegateValue();
        delegateValue.setValidatorAddress("cosmosvaloper14k4pzckkre6uxxyd2lnhnpp8sngys9m6hl6ml7");
        delegateValue.setDelegatorAddress("cosmos16acemvk7mf4393hksx0zwmjsnqptxu7en7s9n4");
        //amount
        Token token = new Token();
        token.setDenom("uatom");
        token.setAmount("1000000");
        delegateValue.setAmount(token);

        MessageDelegateMulti<MsgDelegateValue> messageDelegateMulti = new MessageDelegateMulti<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(delegateValue);
        MessageDelegateMulti[] msgs = new MessageDelegateMulti[1];
        msgs[0]=messageDelegateMulti;

        //整个json
        CosmosDelegateData cosmosDelegateData = new CosmosDelegateData();
        //signData
        cosmosDelegateData.setSignData(cosmosSignData);
        //memo
        cosmosDelegateData.setMode(mode);
        //tx
        CosmosDelegateMulti delegateMulti = new CosmosDelegateMulti();
        cosmosDelegateData.setTx(delegateMulti);
        delegateMulti.setType(tranType);
        delegateMulti.setMemo(memo);
        delegateMulti.setFee(fee);
        delegateMulti.setMsgs(msgs);
        //make sign
        List<Signature> signatures = makeSign(cosmosSignData,accountList,msgs,priKeyVal,pubKeyVal);
        delegateMulti.setSignatures(signatures);

        System.out.println( "sign transaction success!");
        System.out.println(cosmosDelegateData.toJson());
        System.out.println( "sign transaction success!");
    }


    //签名组装交易
    public static List<Signature> makeSign(CosmosSignData cosmosSignData,List<CosmosAccount> accountList ,
                                           MessageDelegateMulti[] msgs,byte[] priKeyVal,byte[] pubKeyVal) {
        List<Signature> signatures = new LinkedList<>();
        try {
            for (int i = 0; i < accountList.size(); i++) {
                final CosmosAccount cosmosAccount = accountList.get(i);

                SignDataDelegateMulti signData = new SignDataDelegateMulti(cosmosAccount.getAccountNumber(),
                        cosmosSignData.getChainId(),
                        cosmosSignData.getFee(),
                        cosmosSignData.getMemo(),
                        msgs,
                        cosmosAccount.getSequence());
                //序列化
                byte[] byteSignData = EncodeUtils.toJsonEncodeBytes(signData);
                //签名
                byte[] sig = Crypto.sign(EncodeUtils.hexStringToByteArray(EncodeUtils.bytesToHex(byteSignData)),
                        Hex.toHexString(priKeyVal));
                String sigResult = Strings.fromByteArray(Base64.encode(sig));
                System.out.println("The result is: " + sigResult);    //expected: /hRJwaM+SH0kLb8x9Uy4TnSeSlnyYzUGKaERQF1ABmMOXA56l7NhphVUISAeyQx06pMHbipaPq4gr6++SCVtpg==
                Signature signature = new Signature();
                Pubkey pubkey = new Pubkey(Strings.fromByteArray(Base64.encode(pubKeyVal)));
                signature.setPubkey(pubkey);
                signature.setSignature(sigResult);
                signatures.add(signature);
            }
            System.out.println( "sign success!");
        } catch (Exception e) {
            System.out.println( "sign failed!");
            System.out.println(e);
        }
        return signatures;
    }
}
