package NIOInduction.AIO.Server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyneTimeServerHandler> {
    public  void completed(AsynchronousSocketChannel result,AsyneTimeServerHandler attachment){
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandler(result));
    }

    public void failed(Throwable exc,AsyneTimeServerHandler attachment){
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
