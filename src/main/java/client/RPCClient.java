package client;

import common.RPCRequest;
import common.RPCResponse;

public interface RPCClient {

    RPCResponse sendRequest(RPCRequest request);
}
