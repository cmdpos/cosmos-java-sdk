package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgCreateValidatorValue;
import io.cosmos.types.CommissionMsg;
import io.cosmos.types.Description;
import io.cosmos.types.Token;

import java.util.ArrayList;
import java.util.List;


//
//  "address": "1FD71AD40042F1484F58692AE2F9CB8112F7E21E",
//          "pub_key": {
//          "type": "tendermint/PubKeyEd25519",
//          "value": "IEBfkI5TjKk39XpIpD4YKNkOAUSxNEJ9o52TIn5WXpY="
//          },
//          "priv_key": {
//          "type": "tendermint/PrivKeyEd25519",
//          "value": "FvdZwfnd4s0XlcjJ3pLJKzpT/wGGknsi8sS8XjlAkjYgQF+QjlOMqTf1ekikPhgo2Q4BRLE0Qn2jnZMiflZelg=="
//          }
//          }
//          evavalconspub1zcjduepqe2vw6dzwdvjt2fskt8gq25p6gn9y6wlpejx8uayr0u5hrvn8xa4qfrl9v8

public class MsgCreateValidator extends MsgBase{

    public static void main(String[] args) {
        MsgCreateValidator msg = new MsgCreateValidator();
        msg.setMsgType("cosmos-sdk/MsgCreateValidator");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");
        Message messages = msg.produceMsg();
        msg.submit(messages, "6", "200000", "cosmos transfer");
    }

    public Message produceMsg() {
        MsgCreateValidatorValue value = new MsgCreateValidatorValue();
        value.setDelegatorAddress("eva1hg40dv5e237qy28vtyum52ygke32ez35syykpz");
        value.setValidatorAddress("evavaloper1hg40dv5e237qy28vtyum52ygke32ez35xexq98");
        value.setPubKey("evavalconspub1zcjduepqe2vw6dzwdvjt2fskt8gq25p6gn9y6wlpejx8uayr0u5hrvn8xa4qfrl9v8");

        Description d = new Description();
        d.setDetails("1");
        d.setIdentity("2");
        d.setMoniker("3");
        d.setWebsite("4");

        CommissionMsg c = new CommissionMsg();
        c.setMaxChangeRate("0.05");
        c.setMaxRate("0.05");
        c.setRate("0.05");

        Token t = new Token();
        t.setAmount("68");
        t.setDenom(EnvInstance.getEnv().GetDenom());

        value.setValue(t);
        value.setCommission(c);
        value.setDescription(d);
        value.setMinSelfDelegation("1");


        Message<MsgCreateValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
