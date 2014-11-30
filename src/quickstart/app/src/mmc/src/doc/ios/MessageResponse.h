//
//  MessageResponse.h
//
//  Created by 代码生成器1.0
//

#import <Foundation/Foundation.h>
#import "Message.h"

#define C_STATUSCODE        @"statusCode"
#define C_STATUSMESSAGE     @"statusMessage"

@interface MessageResponse : Message

@property (nonatomic, copy, readonly) NSString *responseString;
@property (nonatomic, copy, readonly) NSString *type;
@property (nonatomic, copy, readonly) NSString *ver;    //版本
@property (nonatomic, copy, readonly) NSString *seq;    //流水号
@property (nonatomic, copy, readonly) NSString *resCode;    //响应码
@property (nonatomic, copy, readonly) NSString *resMsg; //响应消息
//@property (nonatomic, copy, readonly) NSString *errorMessage; //错误信息（失败的情况）

+ (id)responseWithReponseData:(NSData*)responseData;
- (id)initWithReponseData:(NSData*)responseData;

-(void)setStatusCode:(int)value;
-(int)statusCode;
-(NSString*)statusMessage;

-(void)setErrorMessage:(NSString*)errorMessage;
-(NSString*)errorMessage;

@end
