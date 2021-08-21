package com.myapplication.healthylife.fragments.firstusefragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFirstUseBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstUseFragment extends Fragment {
    private FragmentFirstUseBinding binding;
    private NavController navController;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SharedPreferences sharedPreferences;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = AppPrefs.getInstance(getContext());
        db = new DatabaseHelper(getContext());
        Date date = new Date();
        String now = sdf.format(date);
        binding = FragmentFirstUseBinding.inflate(getLayoutInflater());
        sharedPreferences.edit().putString("date", now).apply();
        initData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.etName.getText().toString().equals("") && validateString(binding.etName.getText().toString())) {
                    if (!binding.etHeight.getText().toString().equals("")
                            && validateFloat(binding.etHeight.getText().toString())
                            && (Float.valueOf(binding.etHeight.getText().toString()) >= 10  && Float.valueOf(binding.etHeight.getText().toString()) <= 300))    {
                        if (!binding.etWeight.getText().toString().equals("")
                                && validateFloat(binding.etWeight.getText().toString())
                                && (Float.valueOf(binding.etWeight.getText().toString()) >= 1 && Float.valueOf(binding.etWeight.getText().toString()) <= 600))    {
                            User user = new User(binding.etName.getText().toString(),
                                    Float.valueOf(binding.etHeight.getText().toString()),
                                    Float.valueOf(binding.etWeight.getText().toString()));

                            double bmi = user.getWeight()/Math.pow(user.getHeight()/100, 2);
                            Log.d("DATA", String.valueOf(bmi));
                            user.setBmi(bmi);

                            sharedPreferences.edit().putBoolean("isLogout", false).apply();

                            sharedPreferences.edit().putString("user", new Gson().toJson(user)).apply();

                            saveListOfExercisesForNewUser(exercises, bmi);

                            navController.navigate(R.id.action_firstUseFragment_to_mainFragment);
                        }else   {
                            binding.etWeight.requestFocus();
                            openKeyboard(binding.etWeight);
                            Toast.makeText(getActivity(), "Invalid Weight", Toast.LENGTH_SHORT).show();
                        }
                    }else   {
                        binding.etHeight.requestFocus();
                        openKeyboard(binding.etHeight);
                        Toast.makeText(getActivity(), "Invalid Height", Toast.LENGTH_SHORT).show();
                    }
                }else   {
                    binding.etName.requestFocus();
                    openKeyboard(binding.etName);
                    Toast.makeText(getActivity(), "Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateString(String str)   {
        Pattern patternString = Pattern.compile("^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" + "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" + "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$");
        Matcher matcher = patternString.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private Boolean validateFloat(String num) {
        Pattern patternFloat = Pattern.compile("([0-9]*[.])?[0-9]+");
        Matcher matcher = patternFloat.matcher(num);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private void initData() {
        exercises.add(new Exercise(-1,"Bridge", "Easy", 90, R.drawable.bridge, new int[]{1, 5}, R.raw.bridge,
                "Đây là nhóm động tác kích thích cơ toàn thân và vùng cơ lưng phía sau cơ thể, nhìn trông giống như 1 cây cầy. Động tác này được xem là cách để khởi động và làm nóng cơ thể khá hoàn hảo",
                "Nằm ngửa lưng trên sàn, hai gối gập lại, lòng bàn chân để trên sàn, hai tay duỗi thẳng 2 bên hông.@Dùng lực bàn chân và gồng cơ toàn thân, nâng người lên khỏi sàn cho tới khi nào hông duỗi thẳng hoàn toàn, siết cứng cơ mông.@Từ từ hạ xuống và lặp lại.@- 15-20 lần/hiệp.@- 3 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Chair Squat", "Moderate", 90, R.drawable.chairsquat, new int[]{2, 3}, R.raw.chairsquat,
                "Squat để tăng cường độ khoẻ cho đôi chân, mông và cơ toàn thân của bạn. Đây là 1 động tác có thể thực hiện ở bất kỳ nơi nào, chỉ cần đặt 1 ghế ngồi ngay phía sau để đảm bảo bạn tập đúng tư thế.",
                "Đứng phía trước ghế, hai chân bằng vai, mũi chân hơi hướng ra 2 bên.@Gập người ở hông và khuỵu gối xuống, hạ người về sau và hướng xuống, cho tới khi mông hơi chạm vào ghế, duỗi thẳng 2 tay ra phía trước.@Dùng lực gót chân, đẩy người đúng dậy và quay trở lại vị trí ban đầu.@- 10-15 lần/hiệp.@- 3 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Ghế (tuỳ chọn)"));

        exercises.add(new Exercise(-1,"Knee Pushup", "Moderate", 90, R.drawable.kneepushup, new int[]{3, 4}, R.raw.kneepushup,
                "Đây là cách hít đất cho người mới bắt đầu tập, giúp bạn xây dựng dần sức mạnh của đôi tay, ngực, và toàn thân.",
                "Vào tư thế Plank cao, hai gối đặt trên sàn.@Duy trì 1 đường thẳng từ đầu tới gối, khuỵu 2 cùi chỏ để hạ người xuống gần chạm sàn. Giữ 2 cùi chỏ gập lại 1 góc 45 độ.@Đẩy ngược lại vị trí ban đầu.@- 10-15 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Stationary Lunge", "Moderate", 60, R.drawable.stationarylunge, new int[]{2, 5}, R.raw.stationarylunge,
                "Động tác này giúp bạn rèn luyện cơ đùi trước, sau, và cơ mông",
                "Đứng hai chân dạng rộng, chân phải ở phía trước. Bàn chân phải đặt cố định trên sàn, mũi bàn chân trái đặt trên sàn.@Khuỵu gối và nhún xuống, dừng lại khi đùi phải song song với sàn.@Đẩy ngược người đứng dậy bằng lực bàn chân phải, để quay trở lại vị trí ban đầu. Sau đó đổi chân.@- 15-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Downward Dog", "Moderate", 90, R.drawable.downwarddog, new int[]{5}, R.raw.downwarddog,
                "Động tác này để kiểm tra vùng thân người trên, đặc biệt là cơ vai.",
                "Vào tư thế Plank cao, hai tay chống xuống sàn, ngay dưới 2 vai, hai bàn chân đặt hơi sát vào nhau.@Đẩy hông lên cao và hạ xuống, thân người bạn sẽ tạo thành 1 hình tam giác với mặt sàn, mắt nhìn về phía 2 chân.@- 15-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Donkey Kick", "Moderate", 60, R.drawable.donkeykick, new int[]{1, 2}, R.raw.donkeykick,
                "Tăng cơ mông với bài tập này là 1 trong những nhóm động tác được nhiều người yêu thích.",
                "Vào tư thế 4 chân, hai tay dưới 2 vai, hai gối dưới hông.@Giữ lưng thẳng, đẩy chân phải thẳng ra phía sau.@Mũi chân gập lại, hướng thẳng xuống sàn. Giữ hông cố định. Siết cứng cơ mông ở đỉnh.@Quay trở lại vị trí ban đầu. Lặp lại cho chân kia.@- 15-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Bird Dog", "Hard", 90, R.drawable.birddog, new int[]{3, 4}, R.raw.birddog,
                "Động tác toàn thân này đòi hỏi khả năng giữ thăng bằng, tư thế này rất phù hợp cho những người thường xuyên tập luyện tại nhà của mình.",
                "Vào tư thế 4 chân, đảm bảo 2 tay ở ngay dưới 2 vai, hai gối ở dưới hông.@Giữ đầu cổ cố định, đồng thời duỗi thẳng tay trái và chân phải. Giữ hông cố định và dừng lại trong 2 giây.@Quay trở lại vị trí ban đầu. Lặp lại cho tay phải và chân trái.@- 15-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Forearm Plank", "Hard", 60, R.drawable.forearmplank, new int[]{2, 4}, R.raw.forearmplank,
                "Động tác này đòi hỏi khả năng giữ thăng bằng và động trực diện vào cơ bụng của bạn.",
                "Vào tư thế Plank trên cẳng tay. Thân người tạo thành 1 đường thẳng, từ đầu tới chân.@Đảm bảo hông và lưng dưới không bị xà xuống sàn. Giữ yên tư thế này trong vòng 30 đến 60 giây.@- 15-30 lần/hiệp.@- 1 hiệp/bài.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Hip Abduction", "Moderate", 90, R.drawable.hipabduction, new int[]{1, 2}, R.raw.hipabduction,
                "Cơ hông đóng vai trò rất quan trọng trong hầu hết các động tác và hoạt động thường ngày, nên bạn cần phải gia tăng thể lực cho vùng này. Động tác này đặc biệt cần thiết cho những ai thường xuyên phải ngồi suốt cả ngày dài.",
                "Nằm nghiêng 1 bên người, chân trái dưới, chân phải trên, hai chân duỗi thẳng.@Nâng chân phải lên, giữ người cố định.@Sau đó, hạ chân phải xuống vị trí ban đầu.@Lặp lại với chân kia.@- 15-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Bicycle Crunch", "Hard", 70, R.drawable.bicyclecrunch, new int[]{1, 3}, R.raw.bicyclecrunch,
                "Đây là 1 trong những động tác cực tốt để đốt cháy calo dư thừa, cũng như tăng cường cơ bụng giúp eo thon, bụng chắc khoẻ.",
                "Nằm ngửa lưng trên sàn, đưa 2 chân lên cao. Gập cùi chỏ và đặt 2 tay ra sau đầu.@Gập người lên và đưa cùi chỏ phải chạm vào gối trái, duỗi thẳng chân phải.@Hạ người xuống vừa phải, gập chân phải và duỗi thẳng chân trái, sau đó kéo cùi chỏ trái chạm vào gối phải. Sau đó, lặp lại.@- 10-15 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));

        exercises.add(new Exercise(-1,"Running", "Moderate", 300, R.drawable.running, new int[]{3, 4, 5}, R.raw.running,
                "Chạy bộ không chỉ giúp tim và phổi của bạn tốt hơn mà còn giúp bạn có một cơ thể săn chắc, khoẻ mạnh, và một tinh thần lạc quan, yêu đời.",
                "Khởi động làm nóng cơ thể trước khi chạy bộ.@Sau đó, bắt đầu chạy bộ trong vòng 5 phút.@Giữ tư thế thẳng lưng, mặt hướng về phía trước, 2 tay đánh thoải mái, và hít thở đều, nhẹ nhàng.@Lưu ý: Bạn nên chạy với tốc độ phù hợp để tránh việc đuối sức, bạn có thể chạy lâu hơn để có hiệu quả tốt hơn.",
                "Giày (tuỳ chọn)"));

        exercises.add(new Exercise(-1,"Skipping Rope", "Moderate", 70, R.drawable.skippingrope, new int[]{4, 5}, R.raw.skippingrope,
                "Nhảy dây giúp bạn đốt cháy calories, mang lại một cơ thể săn chắc, khoẻ mạnh. Đây là một môn thể thao được rất nhiều người ưa thích.",
                "Xếp 2 chân gần nhau, giữ tư thế của bạn thẳng, không còng lưng. Sau đó, bắt đầu nhảy dây và hít thở đều, nhẹ nhàng.@- 25-30 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Dây nhảy (bắt buộc)"));

        exercises.add(new Exercise(-1,"Push Up", "Hard", 90, R.drawable.pushup, new int[]{1, 2}, R.raw.pushup,
                "Hít đất giúp bạn rèn luyện cơ bắp, đặc biệt là cơ tay, ngực và mang lại một thể chất tốt cho cơ thể.",
                "Chống tay xuống sàn, 2 tay nằm dưới 2 vai, 2 chân đặt trên ghế hoặc 1 bục cao, thân người duỗi thẳng.@Hít vào, đồng thời hạ người xuống đến khi ngực sắp chạm sàn.@Đẩy người lên về vị trí ban đầu, siết cơ ngực đồng thời thở mạnh.@Lặp lại.@- 10-20 lần/hiệp.@- 2 hiệp/bài.@- 10 giây nghỉ giữa các hiệp.@- Nghỉ 1 phút để chuyển sang bài tập khác.",
                "Không có"));
    }

    private void saveListOfExercisesForNewUser(ArrayList<Exercise> exercise, double bmi)    {
        boolean startRecommended = false;
        boolean startOthers = false;
        ArrayList<Exercise> result = new ArrayList<>();
        int type;
        if (bmi >= 30) {
            type = 5;
        }else if(bmi >= 25 && bmi <= 29.9) {
            type = 4;
        }else if(bmi >= 23 && bmi <= 24.9)  {
            type = 3;
        }else if(bmi >= 18.5 && bmi <= 22.9)    {
            type = 2;
        }else   {
            type = 1;
        }
        Log.d("DATA", String.valueOf(type));

        //add recommended ex
        for (Exercise ex: exercise)    {
            for (int i: ex.getTypes())  {
                if (i == type && !startRecommended)  {
                    ex.setFirst(true);
                    ex.setRecommended(true);
                    Log.d("REC", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                    result.add(ex);
                    startRecommended = true;
                    break;
                }else if(i == type && startRecommended) {
                    ex.setRecommended(true);
                    Log.d("REC", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                    result.add(ex);
                    break;
                }
            }
        }

        boolean isOthers;
        for (Exercise ex: exercise)    {
            isOthers = true;
            for (int i: ex.getTypes()) {
                if(i == type)    {
                    isOthers = false;
                }
            }
            if (isOthers && !startOthers) {
                ex.setFirst(true);
                ex.setOthers(true);
                Log.d("OTH", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                result.add(ex);
                startOthers = true;
            } else if (isOthers && startOthers) {
                ex.setOthers(true);
                Log.d("OTH", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                result.add(ex);
            }
        }

        for (Exercise ex: result) {
            Log.d("DATA", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
            db.add(ex);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    public void openKeyboard(View view)  {
        InputMethodManager inputMethodManager =  (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(),     InputMethodManager.SHOW_FORCED, 0);
    }
}