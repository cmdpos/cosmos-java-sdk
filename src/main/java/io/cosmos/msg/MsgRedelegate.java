package io.cosmos.msg;

import io.cosmos.msg.delegate.*;
import io.cosmos.types.*;

public class MsgRedelegate extends MsgDelegate {
    String validatorDstAddress;
    public static void main(String[] args) {
        MsgRedelegate msg = new MsgRedelegate();

        msg.setMsgType("cosmos-sdk/MsgBeginRedelegate");
        msg.setValidatorSrcAddress("cosmosvaloper1az394pclywqpaktrnrlpfjal30d9re36gstk8d");
        msg.setValidatorDstAddress("cosmosvaloper14tdm50scshf7d79zt8xgchev2cxkmlq5xtap80");

        msg.submit("stake",
                "3",
                "200000",
                "testchain",
                "stake",
                "100",
                "2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");
    }

    public void setValidatorDstAddress(String addr) {
        this.validatorDstAddress = addr;
    }
    public void setValidatorSrcAddress(String addr) {
        this.setValidatorAddress(addr);
    }

    protected MessageDelegateMulti[] produceDelegateMsg(String delegateDenom, String delegateAmount) {
        //make tx.msg
        MsgBeginRedelegateValue delegateValue = new MsgBeginRedelegateValue();
        delegateValue.setValidatorSrcAddress(validatorAddress);
        delegateValue.setValidatorDstAddress(validatorDstAddress);
        delegateValue.setDelegatorAddress(address);
        Token token = new Token();
        token.setDenom(delegateDenom);
        token.setAmount(delegateAmount);
        delegateValue.setAmount(token);
        MessageDelegateMulti<MsgBeginRedelegateValue> messageDelegateMulti = new MessageDelegateMulti<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(delegateValue);
        MessageDelegateMulti[] msgs = new MessageDelegateMulti[1];
        msgs[0] = messageDelegateMulti;
        return msgs;
    }

}
