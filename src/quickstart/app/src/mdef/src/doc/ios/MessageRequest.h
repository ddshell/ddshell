//
//  MessageRequest.h
//
//  Created by 代码生成器1.0
//

#import <Foundation/Foundation.h>
#import "Message.h"

@class MessageResponse;

@interface MessageRequest : Message {
}
+ (id)request;

+ (NSString*)RA_type;

+ (Class)responseClass;

//最终拼接生成的发送字符串
@property (nonatomic, copy, readonly) NSString *postString;

@end
