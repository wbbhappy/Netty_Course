package Serialization_ProtoBuf.Server;

import Serialization_ProtoBuf.ProtoBuf.PersonProbuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class ReqServerHandler extends ChannelHandlerAdapter{
    public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
        PersonProbuf.Person people  = (PersonProbuf.Person)msg;
        if("Orange".equalsIgnoreCase(people.getName())){
            //if("Orange".equals(people.getName())){
            System.out.println("accept client people:[" + people.toString() + "]");
            ctx.writeAndFlush(resp(people.getId()));
        }
    }

    private PersonProbuf.Person resp(long peopleID){
        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
        builder.setId(peopleID);
        builder.setName("karl");
        builder.setSex("boy");
        builder.setTel("110");
        return builder.build();
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
