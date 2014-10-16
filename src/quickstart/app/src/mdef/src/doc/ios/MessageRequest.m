//
//  MessageRequest.m
//
//  Created by 代码生成器1.0
//

#import "MessageRequest.h"
#import <objc/runtime.h>
#import "MessageResponse.h"


@interface MessageRequest()
{
    NSInteger sequenceNo_;  //流水号
}
@property (nonatomic, copy, readwrite) NSString *postString;

@end

@implementation MessageRequest

+ (id)request{
    MessageRequest *request = [[[self class]alloc]init];
    return request;
}

+ (NSString*)RA_type{
    NSAssert(0, @"子类必须重载%s", __FUNCTION__);
    return @"";
}

+ (Class)responseClass{
    NSAssert(0, @"子类必须重载%s", __FUNCTION__);
    return [MessageResponse class];
}

@end

