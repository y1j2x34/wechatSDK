package y1j2x34.wechat;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.utils.MessageUtils;

public enum MessageType {
	Text {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isTextMessage(message);
		}
	},
	EventLocation {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isEventLocationMessage(message);
		}
	},
	Image {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isImageMessage(message);
		}
	},
	Link {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isLinkMessage(message);
		}
	},
	Subscribe {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isSubscribeMessage(message);
		}
	},
	UnSubscribe {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isUnSubscribeMessage(message);
		}
	},
	Video {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isVideoMessage(message);
		}
	},
	Voice {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isVoiceMessage(message);
		}
	},
	UnSubscribe_DBarCode {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isUnSubscribe_DBarCodeMessage(message);
		}
	},
	Subscribed_DBarCode {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isSubscribed_DBarCodeMessage(message);
		}
	},
	Menu_Click {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isMenuClickMessage(message);
		}
	},
	Menu_View {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isViewMenuClickMessage(message);
		}
	},
	SpeechRecognition {
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isSpeechRecognizeMessage(message);
		}
	},
	ALL {
		@Override
		boolean check(ReqBaseMessage message) {
			return true;
		}
	},
	Event{
		@Override
		boolean check(ReqBaseMessage message) {
			return MessageUtils.isEventMessage(message);
		}
	};
	abstract boolean check(ReqBaseMessage message);
}
