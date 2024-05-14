package com.ling.lingRpc.serializer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import com.google.protobuf.MessageLite;



public class ProtobufSerializer implements Serializer {

    private final ThreadLocal<MessageLite.Builder> builderThreadLocal;

    public ProtobufSerializer() {
        this.builderThreadLocal = ThreadLocal.withInitial(() -> {
            try {
                Method newBuilderMethod = MessageLite.class.getDeclaredMethod("newBuilder");
                return (MessageLite.Builder) newBuilderMethod.invoke(null);
            } catch (Exception e) {
                throw new RuntimeException("无法获取消息的 Builder", e);
            }
        });
    }

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        if (!(object instanceof MessageLite)) {
            throw new IllegalArgumentException("不是protobuf类型");
        }
        return ((MessageLite) object).toByteArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        try {
            if (!MessageLite.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException("不是protobuf类型");
            }
            Method parserMethod = type.getMethod("parser");
            Object parser = parserMethod.invoke(null);
            Method parseFromMethod = parser.getClass().getMethod("parseFrom", byte[].class);
            return (T) parseFromMethod.invoke(parser, bytes);
        } catch (Exception e) {
            throw new IOException("错误解析Protobuf消息", e);
        }
    }
}
