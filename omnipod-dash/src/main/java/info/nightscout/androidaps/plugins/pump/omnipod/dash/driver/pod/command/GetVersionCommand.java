package info.nightscout.androidaps.plugins.pump.omnipod.dash.driver.pod.command;

import java.nio.ByteBuffer;

import info.nightscout.androidaps.plugins.pump.omnipod.dash.driver.pod.command.base.CommandType;
import info.nightscout.androidaps.plugins.pump.omnipod.dash.driver.pod.command.base.HeaderEnabledCommand;
import info.nightscout.androidaps.plugins.pump.omnipod.dash.driver.pod.command.base.builder.HeaderEnabledCommandBuilder;

public final class GetVersionCommand extends HeaderEnabledCommand {
    public static final int DEFAULT_ADDRESS = -1; // FIXME move

    private static final short LENGTH = 6;
    private static final byte BODY_LENGTH = 4;

    GetVersionCommand(int address, short sequenceNumber, boolean multiCommandFlag) {
        super(CommandType.GET_VERSION, address, sequenceNumber, multiCommandFlag);
    }

    @Override public byte[] getEncoded() {
        return appendCrc(ByteBuffer.allocate(LENGTH + HEADER_LENGTH) //
                .put(encodeHeader(address, sequenceNumber, LENGTH, multiCommandFlag)) //
                .put(commandType.getValue()) //
                .put(BODY_LENGTH) //
                .putInt(address) //
                .array());
    }

    @Override public String toString() {
        return "GetVersionCommand{" +
                "commandType=" + commandType +
                ", address=" + address +
                ", sequenceNumber=" + sequenceNumber +
                ", multiCommandFlag=" + multiCommandFlag +
                '}';
    }

    public static final class Builder extends HeaderEnabledCommandBuilder<Builder, GetVersionCommand> {
        @Override protected final GetVersionCommand buildCommand() {
            return new GetVersionCommand(address, sequenceNumber, multiCommandFlag);
        }
    }
}
