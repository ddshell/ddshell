//
//  MessageResponse.m
//
//  Created by 代码生成器1.0
//

#import <objc/runtime.h>
#import "MessageResponse.h"



@interface MessageResponse ()

@property (nonatomic, copy, readwrite) NSString *responseString;
@property (nonatomic, copy, readwrite) NSString *type;
@property (nonatomic, copy, readwrite) NSString *ver;    //版本
@property (nonatomic, copy, readwrite) NSString *seq;    //流水号
@property (nonatomic, copy, readwrite) NSString *resCode;    //响应码
@property (nonatomic, copy, readwrite) NSString *resMsg; //响应消息
//@property (nonatomic, copy, readwrite) NSString *errorMessage; //错误信息（失败的情况）

@end

@implementation MessageResponse

+ (id)responseWithReponseData:(NSData*)responseData {
    MessageResponse *response = [[self class]alloc];
    response = [response initWithReponseData:responseData];
    return response;
}

- (id)initWithReponseData:(NSData*)responseData {
    if ((self = [super init])) {
        self.responseString = [[NSString alloc]initWithData:responseData
                                                   encoding:NSUTF8StringEncoding];
        NSLog(@"响应报文:\n%@", self.responseString);
        [self fromString: self.responseString];
    }
    return self;
}

-(void)setStatusCode:(int)value {
	self.context[C_STATUSCODE] = [NSNumber numberWithInt: value];
}

-(void)setStatusMessage:(NSString*)value {
	self.context[C_STATUSMESSAGE] = value;
}

-(void)setErrorMessage:(NSString*)value {
	self.context[C_ERRORMESSAGE] = value;
}

-(int)statusCode {
	NSNumber *value = self.context[C_STATUSCODE];
	return [value intValue];
}

-(NSString*)statusMessage {
	return self.context[C_STATUSMESSAGE];
}

-(NSString*)errorMessage {
	return self.context[C_ERRORMESSAGE];
}



@end
