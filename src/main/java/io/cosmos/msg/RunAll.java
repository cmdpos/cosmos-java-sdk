package io.cosmos.msg;

import io.cosmos.msg.utils.Message;

public class RunAll {


    public static void main(String[] args) {
        try
        {
            int interval = 10000;


            RunMsgSend();
            Thread.sleep(interval);
            RunMsgDelegate() ;
            Thread.sleep(interval);
            RunMsgRedelegate();
            Thread.sleep(interval);
            RunMsgUnbond() ;
            Thread.sleep(interval);
            RunMsgSetWithdrawAddress();
            Thread.sleep(interval);
            RunMsgWithdrawDelegatorReward() ;
            Thread.sleep(interval);
            RunMsgWithdrawValidatorCommission();

        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    static void RunMsgSend() {

        MsgSend msg = new MsgSend();
        msg.setMsgType("cosmos-sdk/MsgSend");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceSendMsg("stake",
                "16",
                "cosmos1geyy4wtak2q9effnfhze9u4htd8yxxmagdw3q0");

        msg.submit(messages, "6", "200000", "cosmos transfer");
    }

    static void RunMsgDelegate() {

        MsgDelegate msg = new MsgDelegate();
        msg.setMsgType("cosmos-sdk/MsgDelegate");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message message = msg.produceDelegateMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083", "stake", "100");

        msg.submit(message, "3", "200000", "Delegate memo");
    }

    static void RunMsgRedelegate() {

        MsgRedelegate msg = new MsgRedelegate();
        msg.setMsgType("cosmos-sdk/MsgBeginRedelegate");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceDelegateMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083",
                "cosmosvaloper1uhfa9260p3xhnac74wgx5er7tpltww78gxeac0",
                "stake", "100");

        msg.submit(messages,
                "3",
                "200000",
                "Delegate memo");
    }

    static void RunMsgUnbond() {

        MsgUnbond msg = new MsgUnbond();
        msg.setMsgType("cosmos-sdk/MsgUndelegate");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceDelegateMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083",
                "stake", "100");

        msg.submit(messages,
                "3",
                "200000",
                "Delegate memo");
    }

    static void RunMsgSetWithdrawAddress() {

        MsgSetWithdrawAddress msg = new MsgSetWithdrawAddress();
        msg.setMsgType("cosmos-sdk/MsgModifyWithdrawAddress");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceMsg("cosmos1hg40dv5e237qy28vtyum52ygke32ez35hm307h");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos set withdrawAddr");
    }

    static void RunMsgWithdrawDelegatorReward() {

        MsgWithdrawDelegatorReward msg = new MsgWithdrawDelegatorReward();
        msg.setMsgType("cosmos-sdk/MsgWithdrawDelegationReward");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos set withdrawAddr");
    }

    static void RunMsgWithdrawValidatorCommission() {

        MsgWithdrawValidatorCommission msg = new MsgWithdrawValidatorCommission();
        msg.setMsgType("cosmos-sdk/MsgWithdrawValidatorCommission");
        String mnemonic = "sentence deputy little switch fiction balcony hollow iron net index sound hollow scare attitude only cushion best candy wonder phone napkin sketch announce derive";
        msg.initMnemonic(mnemonic);

        Message messages = msg.produceMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos withdraw");
    }
}