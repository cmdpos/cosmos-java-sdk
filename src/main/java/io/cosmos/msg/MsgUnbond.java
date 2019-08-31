package io.cosmos.msg;

public class MsgUnbond extends MsgDelegate {

    public static void main(String[] args) {
        MsgUnbond msg = new MsgUnbond();

        msg.setMsgType("cosmos-sdk/MsgUndelegate");
        msg.setValidatorAddress("cosmosvaloper1uhfa9260p3xhnac74wgx5er7tpltww78gxeac0");

        msg.submit("stake",
                "3",
                "200000",
                "testchain",
                "stake",
                "160",
                "2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");
    }

}
