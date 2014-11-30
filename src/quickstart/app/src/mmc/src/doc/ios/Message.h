//
//  Message.h
//
//  Created by 代码生成器1.0
//

#import <Foundation/Foundation.h>
#import "BeanContext.h"

#define C_MESSAGEID      @"messageId"
#define C_SESSIONID      @"sessionId"
#define C_ERRORMESSAGE   @"errorMessage"


@interface Message : NSObject

@property(nonatomic, strong) NSMutableDictionary *context; 

- (void) fromString:(NSString*)message;
- (NSString*) toString;

- (void) setMessageId:(NSString*)value;
- (NSString*) messageId;

- (void) setSessionId:(NSString*)value;
- (NSString*) sessionId;

@end
