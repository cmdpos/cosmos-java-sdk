package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgEditValidatorValue;
import io.cosmos.types.CommissionMsg;
import io.cosmos.types.Description;
import io.cosmos.types.Token;

public class MsgEditValidator extends MsgBase {

    public static void main(String[] args) {
        MsgEditValidator msg = new MsgEditValidator();
        msg.setMsgType("cosmos-sdk/MsgEditValidator");
        msg.initMnemonic(EnvInstance.getEnv().GetNode0Mnmonic());

        Message messages = msg.produceMsg();

        msg.submit(messages, "6", "200000", "");
    }

    public Message produceMsg() {

        Description d = new Description();
        d.setDetails("1");
        d.setIdentity("1");
        d.setMoniker("m1");
        d.setWebsite("1");

        MsgEditValidatorValue value = new MsgEditValidatorValue();

        value.setAddress(EnvInstance.getEnv().GetNode0ValAddr());
//        value.setCommissionRate("0.6");
        value.setDescription(d);
        value.setMinSelfDelegation("1100");


        Message<MsgEditValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
// {"mode":"block","tx":{"msg":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"moniker":"m1","identity":"1","website":"1","details":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"AgYaL1tZ7ekqvweQhKojG8sDHUfN23qJWviAsTDIWvYU"},"signature":"GsIJ6SQkC1srfAXHntvRoInb0iO1d0iW0FEQlDLZiW4xr0Tc75acC8cVuOmAq6p7QaxSbhK9rGh82i+yIi+9ww=="}]}}
// {"mode":"block","tx":{"memo":"","msg":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"details":"1","identity":"1","moniker":"m1","website":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"AgYaL1tZ7ekqvweQhKojG8sDHUfN23qJWviAsTDIWvYU"},"signature":"UYORrYfcP0R/SKtF/G2TA4LBw+h/45gGPhwQ6r5MdphHEkWC+q1lS9tyaw472MNI11MjE5g80hlGAbJ4waqYtg=="}]}}
// {"mode":"block","tx":{"memo":"","msg":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"moniker":"m1","identity":"1","website":"1","details":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"AgYaL1tZ7ekqvweQhKojG8sDHUfN23qJWviAsTDIWvYU"},"signature":"GsIJ6SQkC1srfAXHntvRoInb0iO1d0iW0FEQlDLZiW4xr0Tc75acC8cVuOmAq6p7QaxSbhK9rGh82i+yIi+9ww=="}]}}
// {"mode":"block","tx":{"memo":"","msg":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"details":"1","identity":"1","moniker":"m1","website":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"AgYaL1tZ7ekqvweQhKojG8sDHUfN23qJWviAsTDIWvYU"},"signature":"GsIJ6SQkC1srfAXHntvRoInb0iO1d0iW0FEQlDLZiW4xr0Tc75acC8cVuOmAq6p7QaxSbhK9rGh82i+yIi+9ww=="}]}}
// {"account_number":"1","chain_id":"okchain","memo":"","msgs":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"moniker":"m1","identity":"1","website":"1","details":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"sequence":"11"}
// {"account_number":"1","chain_id":"okchain","memo":"","msgs":[{"type":"cosmos-sdk/MsgEditValidator","value":{"Description":{"details":"1","identity":"1","moniker":"m1","website":"1"},"address":"okchainvaloper10q0rk5qnyag7wfvvt7rtphlw589m7frs863s3m","min_self_delegation":"1100"}}],"sequence":"10"}